package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeEntity;
import com.ljx.hfgsjt.main.service.WfReNodeService;
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
@RequestMapping(value = "WfReNodeController")
public class WfReNodeController {

    @Autowired
    private WfReNodeService service;

    @RequestMapping(value = "getWfReNode",method = RequestMethod.GET)
    @ResponseBody
    public String getWfReNode(String nodename,Integer page,Integer size, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        PageRequest pageRequest = PageRequest.of(page, size);
        if(nodename == null){
            nodename = "";
        }
        Page<WfReNodeEntity> nodepage = service.findByNameLike(nodename,pageRequest);
        jo.put("total",nodepage.getTotalElements());
        jo.put("row",nodepage.getContent());
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteNodeById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNodeById(String id, HttpServletResponse response) {
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
    public String save(WfReNodeEntity nodeEntity, HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        try {
            if(nodeEntity.getActivityid() == null || nodeEntity.getActivityid().equals("")){
                nodeEntity.setActivityid(GuidUtils.getGuid());
            }
            service.save(nodeEntity);
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }


}
