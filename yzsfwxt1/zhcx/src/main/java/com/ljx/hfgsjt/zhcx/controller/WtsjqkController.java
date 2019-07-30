package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;
import com.ljx.hfgsjt.zhcx.service.WtsjqkService;
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
@RequestMapping(value = "WtsjqkController")
@Api(description = "委托设计情况")
public class WtsjqkController {
    @Autowired
    private WtsjqkService service;

    @ApiOperation(value = "获取委托设计情况")
    @RequestMapping(value = "getWtsjQk",method = RequestMethod.GET)
    public String  getWtsjQk(WtsjqkModel model){
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

/* for(WtsjqkModel wm:list) {
               WtsjqkModel mo = new WtsjqkModel();
                   mo=service.getotherList(wm.getId());
                   if(mo!=null){
                       wm.setWtsj(mo.getWtsj());
                   }
               list2.add(wm);
           }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }
}
