package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.HtqklbModel;
import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;
import com.ljx.hfgsjt.zhcx.service.HtqklbService;
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
@RequestMapping(value = "HtqklbController")
@Api(description = "合同情况列表")
public class HtqklbController {
    @Autowired
    private HtqklbService service;

    @ApiOperation(value = "获取合同情况列表")
    @RequestMapping(value = "getHtqklb",method = RequestMethod.GET)
    public String  getHtqklb(HtqklbModel model){
        List<WtsjqkModel> list = new ArrayList<WtsjqkModel>();
        List<WtsjqkModel> list2=new ArrayList<WtsjqkModel>();
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
