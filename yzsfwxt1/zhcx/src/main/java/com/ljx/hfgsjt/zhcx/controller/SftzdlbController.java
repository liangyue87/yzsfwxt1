package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.SftzdlbModel;
import com.ljx.hfgsjt.zhcx.service.SftzdlbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "SftzdlbController")
@Api(description = "收费通知单列表")
public class SftzdlbController {
    @Autowired
    private SftzdlbService service;

    @ApiOperation(value = "获取收费通知单列表")
    @RequestMapping(value = "getSftzdlb",method = RequestMethod.GET)
    public String  getSftzdlb(SftzdlbModel model){
        List<SftzdlbModel> list = new ArrayList<>();
        List<SftzdlbModel> list2= new ArrayList<>();
        int total = 0;
        model.setStartRow((model.getPage()-1)*model.getSize());
        model.setEndRow(model.getSize()*model.getPage());
        try {
            total = service.getProjectCount(model);
            if(total>0){
                list = service.getProjectList(model);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }
}
