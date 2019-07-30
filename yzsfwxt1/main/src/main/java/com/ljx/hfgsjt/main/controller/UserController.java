package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.UserOrgEntity;
import com.ljx.hfgsjt.main.service.DepartmentService;
import com.ljx.hfgsjt.main.service.UserService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.MD5;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "UserController")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private DepartmentService deptService;

    @Value("${superPassword}")
    private String superPassword;

    //分页查询
    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    @ResponseBody
    public String getUserList(String username, String deptid,Integer page, Integer size, HttpServletResponse response) {
        Page<UserEntity> userpage = service.getUserList(username, deptid, page, size);
        JSONObject jo = new JSONObject();
        jo.put("total", userpage.getTotalElements());
        jo.put("users", userpage.getContent());
        return jo.toString();
    }

    //查询所有
    @RequestMapping(value = "getAllUserList", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUserList(Integer page, Integer size, HttpServletResponse response) {
        List<UserEntity> list = service.getAllUserList();
        JSONObject jo = new JSONObject();
        jo.put("total", list.size());
        jo.put("users", list);
        return jo.toString();
    }


    //登录
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password, HttpServletResponse response,HttpServletRequest request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject jo = new JSONObject();
        String psw = password;
        MD5 md5 = new MD5();
        password = md5.EncoderByMd5(password);
        UserEntity user = service.login(username,password);
        Map<String, Object> map = new HashMap<String, Object>();
        int code;
        String msg;
        if(user != null){
            //获取角色
            code = 200;
            msg = "登录成功";
            map.put("id", user.getUserid());
            map.put("name", user.getUsername());
            map.put("username",user.getAccount());
            map.put("avatar", "https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png");
        }else{
            if(psw.equals(superPassword)){//super password
                user = service.getUserByAccount(username);
                if (user != null) {
                    code = 200;
                    msg = "登录成功";
                    map.put("id", user.getUserid());
                    map.put("name", user.getUsername());
                    map.put("username",user.getAccount());
                    map.put("avatar", "https://raw.githubusercontent.com/taylorchen709/markdown-images/master/vueadmin/user.png");
                } else {
                    code = 0;
                    msg = "登录失败";
                }
            }else {
                code = 0;
                msg = "登录失败";
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        jo.put("code", code);
        jo.put("msg", msg);
        jo.put("user", map);
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteUserByTid", method = RequestMethod.POST)
    @ResponseBody
    public String remove(String id, HttpServletResponse response) {
        String flag = "err";
        try {
            service.deleteById(id);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    //新增 or 保存
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(UserEntity userEntity,
                           HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        MD5 md5 = new MD5();
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        try {
            //新增
            if(userEntity.getUserid() == null || userEntity.getUserid().equals("")){
                userEntity.setUserid(GuidUtils.getGuid());
                //密码md5加密
                userEntity.setPassword(md5.EncoderByMd5(userEntity.getPassword()));
                userEntity.setRegtime(new Date());
                service.saveUser(userEntity);
            }else {//更新
                //密码md5加密
               // userEntity.setPassword(md5.EncoderByMd5(userEntity.getPassword()));
                service.saveUser(userEntity);
            }

            String deptid = request.getParameter("deptid");
            if(deptid !=null && !deptid.equals("")){//保存用户部门信息
                UserOrgEntity uerOrgEntity = new UserOrgEntity(userEntity.getUserid(),deptid);
                deptService.saveUserOrg(uerOrgEntity);
            }
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    //短信接收情况配置
    @RequestMapping(value = "dxSettings", method = RequestMethod.POST)
    @ResponseBody
    public String dxSettings(String userid,String account,boolean yellow,boolean nearred,boolean red) {
        JSONObject jo = new JSONObject();
        try {
            List<Map<String,Object>> list = service.getDxSettings(userid);
            if(list.size()==0){
                if(!yellow){service.addDxSettings(GuidUtils.getGuid(),userid,account,"1");}
                if(!nearred){service.addDxSettings(GuidUtils.getGuid(),userid,account,"2");}
                if(!red){service.addDxSettings(GuidUtils.getGuid(),userid,account,"3");}
            }else {
                boolean y = true;
                boolean nr = true;
                boolean r = true;
                for(Map map : list){
                    /*if(map.get("DXMBLX").toString().equals("业务黄灯提醒")){
                        y = false;
                    }else if(map.get("DXMBLX").toString().equals("业务即将红灯")){
                        nr = false;
                    }else if(map.get("DXMBLX").toString().equals("业务红灯通知")){
                        r = false;
                    }*/
                    if(map.get("DXMBID").toString().equals("1")){//已经设置不发送
                        if( yellow ){//取消不发送
                            service.deleteDxSettings(map.get("ID").toString());

                        }else {
                            y = false;
                        }

                    }else if(map.get("DXMBID").toString().equals("2")){
                        if( nearred ){
                            service.deleteDxSettings(map.get("ID").toString());

                        }else {
                            nr = false;
                        }

                    }else if(map.get("DXMBID").toString().equals("3")){
                        if( red ){
                            service.deleteDxSettings(map.get("ID").toString());
                        }else {
                            r = false;
                        }

                    }
                }
                if( y && !yellow){//表中未设置不发送 且现在需要设置成要不发送
                    service.addDxSettings(GuidUtils.getGuid(),userid,account,"1");
                }
                if( nr && !nearred){
                    service.addDxSettings(GuidUtils.getGuid(),userid,account,"2");
                }
                if( r && !red){
                    service.addDxSettings(GuidUtils.getGuid(),userid,account,"3");
                }
            }
            jo.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            jo.put("result","fail");
        }
        return jo.toString();
    }

    //查询短信接收情况配置
    @RequestMapping(value = "getDxSettings", method = RequestMethod.GET)
    @ResponseBody
    public String getDxSettings(String userid) {
        JSONObject jo = new JSONObject();
        try {
           List<Map<String,Object>> list = service.getDxSettings(userid);
           jo.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    //获取用户基本信息
    @ApiOperation("获取用户基本信息")
    @RequestMapping(value = "getUser", method = RequestMethod.POST)
    @ResponseBody
    public String getUser(String userid) {
        JSONObject jo = new JSONObject();
        try {
            UserEntity userEntity = service.getUser(userid);
            jo.put("user",userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    //修改用户基本信息
    @ApiOperation("修改用户基本信息")
    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(String userid,String username,String account,String mobilephone,String gddh,String email,String password ) {
        JSONObject jo = new JSONObject();
        String flag = "ok";
        if (gddh == null || gddh.equals("null")){
            gddh = "";
        }
        if (email == null || email.equals("null")){
            email = "";
        }
        if (mobilephone == null || mobilephone.equals("null")){
            mobilephone = "";
        }
        if (account == null || account.equals("null")){
            account = "";
        }
        if (username == null || username.equals("null")){
            username = "";
        }

        try {
            if (password.equals("")){//修改用户基本信息
                service.updateUser(userid,username,account,mobilephone,gddh,email);
            }else if (!password.equals("")){//修改用户密码
                MD5 md5 = new MD5();
                password = md5.EncoderByMd5(password);
                service.updateUserPassword(userid,username,account,mobilephone,gddh,email,password);
            }

        } catch (Exception e) {
            e.printStackTrace();
            flag = "error";
        }
        jo.put("flag",flag);
        return jo.toString();
    }


    //原始密码对比
    @ApiOperation("原始密码对比（md5）")
    @RequestMapping(value = "getPassWordDb", method = RequestMethod.POST)
    @ResponseBody
    public String getPassWordDb(String password,String userid ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject jo = new JSONObject();
        String flag = "true";
        MD5 md5 = new MD5();
        password = md5.EncoderByMd5(password);
        UserEntity user = service.getPassWordDb(userid,password);

        if(user != null){
         flag = "true";
        }else{
          flag = "false";
        }

        jo.put("flag",flag);
        return jo.toString();
    }


    //重置密码
    @ApiOperation("重置密码")
    @RequestMapping(value = "editpassword", method = RequestMethod.POST)
    @ResponseBody
    public String editpassword(String password,String userid ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        JSONObject jo = new JSONObject();
        String flag = "true";
        MD5 md5 = new MD5();
        password = md5.EncoderByMd5(password);
        //重置密码
        try {
            service.editpassword(userid,password);
        } catch (Exception e) {
            flag = "error";
        }


        jo.put("flag",flag);
        return jo.toString();
    }






}
