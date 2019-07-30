package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.WfDfActivityEntity;
import com.ljx.hfgsjt.main.service.WfDfActivityService;
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
@RequestMapping(value = "WfDfActivityController")
public class WfDfActivityController {

    @Autowired
    private WfDfActivityService service;

    @RequestMapping(value = "getDfActivity",method = RequestMethod.GET)
    @ResponseBody
    public String getDfActivity(String activityname,int page,int size, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        PageRequest pageRequest = PageRequest.of(page, size);
        if(activityname == null){
            activityname = "";
        }
        Page<WfDfActivityEntity> activityRolepage = service.findByDfActivitynameLike(activityname,pageRequest);
        jo.put("total",activityRolepage.getTotalElements());
        jo.put("row",activityRolepage.getContent());
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteDfActivityByTid", method = RequestMethod.POST)
    @ResponseBody
    public String remove(String activityid, HttpServletResponse response) {
        String flag = "err";
        try {
            service.deleteById(activityid);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    //新增 or 保存
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(WfDfActivityEntity sfDfActivityEntity, HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        try {
            if(sfDfActivityEntity.getActivityid() == null || sfDfActivityEntity.getActivityid().equals("")){
                sfDfActivityEntity.setActivityid(GuidUtils.getGuid());
            }
            service.save(sfDfActivityEntity);
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

}
