package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.TftzdlbModel;
import com.ljx.hfgsjt.zhcx.service.TftzdlbService;
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
@RequestMapping(value = "TftzdlbController")
@Api(description = "退费通知单列表")
public class TftzdlbController {
    @Autowired
    private TftzdlbService service;

    @ApiOperation(value = "获取退费通知单列表")
    @RequestMapping(value = "getTftzdlb",method = RequestMethod.GET)
    public String  getTftzdlb(TftzdlbModel model){
        List<TftzdlbModel> list = new ArrayList<>();
        List<TftzdlbModel> list2= new ArrayList<>();
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
