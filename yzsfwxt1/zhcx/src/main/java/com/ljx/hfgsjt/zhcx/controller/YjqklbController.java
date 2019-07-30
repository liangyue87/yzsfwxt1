package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.TftzdlbModel;
import com.ljx.hfgsjt.entity.zhcx.YjqklbModel;
import com.ljx.hfgsjt.zhcx.service.TftzdlbService;
import com.ljx.hfgsjt.zhcx.service.YjqklbService;
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
@RequestMapping(value = "YjqklbController")
@Api(description = "移交情况列表")
public class YjqklbController {
    @Autowired
    private YjqklbService service;

    @ApiOperation(value = "获取移交情况列表")
    @RequestMapping(value = "getYjqklb",method = RequestMethod.GET)
    public String  getYjqklb(YjqklbModel model){
        List<YjqklbModel> list = new ArrayList<>();
        List<YjqklbModel> list2= new ArrayList<>();
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
