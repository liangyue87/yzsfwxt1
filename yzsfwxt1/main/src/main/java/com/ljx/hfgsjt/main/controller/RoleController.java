package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.RoleEntity;
import com.ljx.hfgsjt.entity.dto.RoleMenuEntity;
import com.ljx.hfgsjt.main.service.RoleMenuService;
import com.ljx.hfgsjt.main.service.RoleService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "RoleController")
public class RoleController {
    @Autowired
    private RoleService service;
    @Autowired
    private RoleMenuService roleMenuservice;

    //分页查询
    @RequestMapping(value = "getRoleList",method = RequestMethod.GET)
    @ResponseBody
    public String getRoleList(String rolename,Integer page, Integer size,HttpServletResponse response){
        System.out.println(page);
        List<RoleEntity> list = service.getPageRoleList(rolename,page, size);

        JSONObject jo = new JSONObject();
        jo.put("total",service.getPages());
        jo.put("roles",list);
        return jo.toString();
    }
/*
    //查询所有
    @RequestMapping(value = "getAllRoleList",method = RequestMethod.GET)
    @ResponseBody
    *//*接收参数*//*
    public String getAllRoleList(Integer page, Integer size,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        List<RoleEntity> list = service.getAllRoleList();
        JSONObject jo = new JSONObject();
        jo.put("total",list.size());
        jo.put("roles",list);
        return jo.toString();
    }*/
    //新增
    @RequestMapping(value = "addRole",method = RequestMethod.POST)
    @ResponseBody
    public String addRole(RoleEntity roleEntity, HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity1 userEntity = (UserEntity1) request.getParameterMap();
        try{
            service.saveRole(roleEntity);
        }catch (Exception e){
            flag = "No";
            e.printStackTrace();
        }
        return flag;
    }
    //删除
    @RequestMapping(value = "delrole",method = RequestMethod.POST)
    @ResponseBody
    public String delrole(String id,HttpServletResponse response){
        String flag = "删除成功";
        try {
            service.deleteById(id);
        } catch (Exception e) {
            flag = "no";
            e.printStackTrace();
        }
        return flag;
    }
    //修改
    @RequestMapping(value = "updateRole",method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(RoleEntity roleEntity, HttpServletRequest request,HttpServletResponse response) {
        String flag = "修改成功";
        try {
            service.updateRole(roleEntity);
        } catch (Exception e) {
            flag = "修改失败/方法异常";
            e.printStackTrace();
        }
        return flag;
    }

    //查询某个角色下分配的菜单
    @RequestMapping(value = "getMenuForRole",method = RequestMethod.GET)
    @ResponseBody
    public String getMenuForRole(String roleid,HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        String menuid = "";
        try {
            List<RoleMenuEntity> list = new ArrayList();
            list = roleMenuservice.getMenuForRole(roleid);
            for(RoleMenuEntity rm : list){
                if(menuid.equals("")){
                    menuid += rm.getMenuid();
                }else{
                    menuid += "," + rm.getMenuid();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("menuid",menuid);
        return jo.toString();
    }

    //保存角色分配菜单模块
    @RequestMapping(value = "saveMenuForRole",method = RequestMethod.POST)
    @ResponseBody
    public String saveMenuForRole(String roleid,String menuid,HttpServletResponse response) {
        String result = "";
        try {
            // 删除模块
            int i  = roleMenuservice.deleteByRoleid(roleid);
            if(!menuid.equals("")){
                String [] menus = menuid.split(",");
                List<RoleMenuEntity> list = new ArrayList();
                for(String str : menus){
                    RoleMenuEntity rm = new RoleMenuEntity(GuidUtils.getGuid(),roleid,str);
                    list.add(rm);
                }
                if(list.size() > 0){
                    roleMenuservice.saveRoleMenu(list);
                }
            }
            result = "success";
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        return result;
    }

    //查询某个角色下分配的菜单
    @RequestMapping(value = "getUserForRole",method = RequestMethod.GET)
    @ResponseBody
    public String getUserForRole(String roleid,HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        String menuid = "";
        try {
            List<Map<String,Object>> list = new ArrayList();
            list = service.getUserForRole(roleid);
            for(Map rm : list){
                if(menuid.equals("")){
                    menuid += rm.get("USERID");
                }else{
                    menuid += "," + rm.get("USERID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("userid",menuid);
        return jo.toString();
    }

    //保存角色分配人员模块
    @RequestMapping(value = "saveUserForRole",method = RequestMethod.POST)
    @ResponseBody
    public String saveUserForRole(String roleid,String userid,HttpServletResponse response) {
        String result = "";
        try {
            // 删除模块
            int i  = service.deleteByRoleid(roleid);
            if(!userid.equals("")){
                String [] menus = userid.split(",");
                //List<Map<String,String>> list = new ArrayList();
                for(String str : menus){
                    Map<String,String> map = new HashMap<String,String>();
                    map.put("roleid",roleid);
                    map.put("userid",str);
                    service.saveRoleUser(map);
                }
            }
            result = "success";
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取人员树
     * @return
     */
    @RequestMapping(value = "getAssignUser",method = RequestMethod.GET)
    @ResponseBody
    public String getAssignUser() {
        List<Map<String,Object>> list=service.getOrgList();
        List<Map<String,Object>> ulist=service.getUserList();
        String str= "["+getOrgUser_json(list,ulist,1,"")+"]";
        return str;
    }

    private String getOrgUser_json(List<Map<String,Object>> list,List<Map<String,Object>> ulist,int k,String pID){
        if(k>5){
            return "";
        }
        StringBuilder note_s=new StringBuilder("");

        for(Map _org :list)
        {
            String parentid = "";
            if(_org.get("PARENTID") != null){
                parentid = _org.get("PARENTID").toString();
            }
            if(StringUtil.isNull(parentid).equals(StringUtil.isNull(pID))){
                if(note_s.length()!=0){
                    note_s.append(",");
                }
                note_s.append("{\"id\":\""+_org.get("ORGID")+"\""
                        +",\"text\":\""+_org.get("ORGNAME")+"\""
                        +",\"icon\":\"el-icon-house\""
                        +",\"children\":["+getOrgUser_json(list,ulist,k+1,_org.get("ORGID").toString())+"]"
                        +"}");

            }
        }
        for(Map _user:ulist){
            String orgid = "";
            if(_user.get("ORGID") != null){
                orgid = _user.get("ORGID").toString();
            }
            if(StringUtil.isNull(orgid).equals(StringUtil.isNull(pID))){
                if(note_s.length()!=0){
                    note_s.append(",");
                }

                note_s.append("{\"id\":\""+_user.get("USERID")+"\""
                        +",\"text\":\""+_user.get("USERNAME")+"\""
                        +",\"icon\":\"el-icon-s-custom\""
                        +",\"children\":[]"
                        +"}");
            }
        }
        return note_s.toString();
    }


}
