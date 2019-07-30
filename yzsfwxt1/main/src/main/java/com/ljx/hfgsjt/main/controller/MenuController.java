package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.MenuEntity;
import com.ljx.hfgsjt.main.service.MenuService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "MenuController")
public class MenuController {
    @Autowired
    private MenuService service;

    /**
     * 获取所有的菜单列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getMenuTree",method = RequestMethod.GET)
    @ResponseBody
    public String menutree(HttpServletRequest request,HttpServletResponse response){
        MenuEntity sdmenu = new MenuEntity();
        List<MenuEntity> list=service.getAllMenuList();
        String str= "["+get_json(list,1,"")+"]";
        return str;
    }

    /***
     * 新增 or 保存
     * @param menuEntity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "saveMenu",method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(MenuEntity menuEntity,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity1 userEntity = (UserEntity1) request.getParameterMap();
        try{
            if(menuEntity.getMenuid().equals("")){
                menuEntity.setMenuid(GuidUtils.getGuid());
            }
            service.saveMenu(menuEntity);
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     */
    @RequestMapping(value = "getMenuById",method = RequestMethod.GET)
    @ResponseBody
    public String saveUser(String menuid,
                           HttpServletRequest request,HttpServletResponse response) {
        MenuEntity menuEntity = service.getMenuById(menuid);
        JSONObject jo = new JSONObject();
        jo.put("menuEntity", menuEntity);
        return jo.toString();
    }


    /**
     * 菜单重新格式化（层级关系）
     * @param list
     * @param k
     * @param pID
     * @return
     */
    private String get_json(List<MenuEntity> list, int k, String pID){
        if(k>5){
            return "";
        }
        StringBuilder note_s=new StringBuilder("");
        for(MenuEntity _menuEntity :list)
        {
            if(StringUtil.isNull(_menuEntity.getParentid()).equals(StringUtil.isNull(pID))){
                if(note_s.length()!=0){
                    note_s.append(",");
                }
                note_s.append("{\"id\":\""+ _menuEntity.getMenuid()
                        +"\",\"text\":\""+ _menuEntity.getMenuname()
                        +"\",\"url\":\""+ _menuEntity.getMenuurl()
                        +"\",\"icon\":\""+ _menuEntity.getMenuicon()
                        +"\",\"children\":["+get_json(list,k+1, _menuEntity.getMenuid())
                        +"]"
                        +",\"attributes\":{\"VALID\":"+ _menuEntity.getValid()
//						+"\",\"ord\":"+_menuEntity.getORDINAL()
//						+",\"url\":\""+_menuEntity.getMENUURL()
//						+"\",\"tar\":\""+_menuEntity.getTARGET()
//						+"\",\"big\":\""+_menuEntity.getBIGICON()
//						+"\",\"ico\":\""+_menuEntity.getMENUICON()
//						+"\",\"vid\":"+_menuEntity.getVALID()
                        +"}"
                        +"}");
            }
        }
        return note_s.toString();
    }

    //删除
    @RequestMapping(value = "deleMenu",method = RequestMethod.POST)
    @ResponseBody
    public String delrole(String id,HttpServletResponse response){
        String flag = "ok";
        try {
            service.deleteById(id);
        } catch (Exception e) {
            flag = "no";
            e.printStackTrace();
        }
        return flag;
    }

    //获取用户的菜单列表
    @RequestMapping(value = "getMenuByUserid",method = RequestMethod.GET)
    @ResponseBody
    public String getMenuByUserid(String userid,HttpServletResponse response){
        List<MenuEntity> list = service.getMenuByUserid(userid);//ab5a2a4bd2364rr1a60aee88697474f3
        List<String> list_parent = new ArrayList<String>();//已经存在的父级菜单
        List<String> list_all = new ArrayList<String>();//所有的父级菜单
        List<String> list_new = new ArrayList<String>();//还未加入的父级菜单
        for(MenuEntity menuEntity : list){
            if(menuEntity.getMenuurl() ==null || menuEntity.getMenuurl().equals("/Home")){//已经添加进来的父级菜单
                list_parent.add(menuEntity.getMenuid());
            }
            if(menuEntity.getParentid() != null){//查找所有的父级菜单
                String str = StringUtils.join(list_all.toArray());
                if(str.indexOf(menuEntity.getParentid()) < 0){
                    list_all.add(menuEntity.getParentid());
                }
            }
        }
        for(String menuId : list_all){
            String str = StringUtils.join(list_parent.toArray());
            if(str.indexOf(menuId) < 0){
                list_new.add(menuId);
            }
        }
        for(String id : list_new){
            MenuEntity mm = service.getMenuById(id);
            if(mm.getParentid() != null){
                String str = StringUtils.join(list_new.toArray());
                if(str.indexOf(mm.getParentid()) < 0){
                    list_new.add(mm.getParentid());
                }
            }
            list.add(mm);
        }
        Collections.sort(list);//排序
        String str = "["+get_json(list,1,"")+"]";
        return str;
    }


}
