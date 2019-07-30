package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.SbcjEntity;
import com.ljx.hfgsjt.main.service.SbcjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "SbcjController")
public class SbcjController {
    @Autowired
    private SbcjService service;

    //分页查询
    @RequestMapping(value = "getSbcjList",method = RequestMethod.GET)
    @ResponseBody
    public String getSbcjList(String sbcjname,Integer page, Integer size,HttpServletRequest request,HttpServletResponse response){
       // List<SbcjEntity> list  = service.getAllSbcjList();
        if(sbcjname == null ){
            sbcjname="";
        }
        List<SbcjEntity> list = service.getPageSbcjList(sbcjname,page, size);
        JSONObject jo = new JSONObject();
        jo.put("total",service.getPages());
        jo.put("sbcj",list);
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
    @RequestMapping(value = "addSbcj",method = RequestMethod.POST)
    @ResponseBody
    public String addSbcj(SbcjEntity sbcjEntity, HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        try{
            if (sbcjEntity.getBh().equals("")){
                return flag;
            }else{
                service.saveSbcj(sbcjEntity);
            }

        }catch (Exception e){
            flag = "No";
            e.printStackTrace();
        }
        return flag;
    }
    //删除
    @RequestMapping(value = "deleSbcj",method = RequestMethod.POST)
    @ResponseBody
    public String deleSbcj(String id,HttpServletResponse response){
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
    @RequestMapping(value = "updateSbcj",method = RequestMethod.POST)
    @ResponseBody
    public String updateSbcj(SbcjEntity sbcjEntity, HttpServletRequest request,HttpServletResponse response) {
        String flag = "修改成功";
        try {
            service.updateSbcj(sbcjEntity);
        } catch (Exception e) {
            flag = "修改失败/方法异常";
            e.printStackTrace();
        }
        return flag;
    }
}
