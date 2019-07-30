package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import com.ljx.hfgsjt.main.service.WfActivityRoleService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "WfActivityRoleController")
public class WfActivityRoleController {

    @Autowired
    private WfActivityRoleService service;

    @RequestMapping(value = "getActivityRole",method = RequestMethod.GET)
    @ResponseBody
    public String getActivityRole(String activityid,int page,int size, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        PageRequest pageRequest = PageRequest.of(page, size);
        if(activityid == null){
            activityid = "";
        }
        Page<WfActivityRoleEntity> activityRolepage = service.findByActivitynameLike(activityid,pageRequest);
        jo.put("total",activityRolepage.getTotalElements());
        jo.put("row",activityRolepage.getContent());
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteActivityRoleByTid", method = RequestMethod.POST)
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
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(WfActivityRoleEntity wfActivityRoleEntity, HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        try {
            if(wfActivityRoleEntity.getWybs() == null || wfActivityRoleEntity.getWybs().equals("")){
                wfActivityRoleEntity.setWybs(GuidUtils.getGuid());
            }
            service.save(wfActivityRoleEntity);
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

}
