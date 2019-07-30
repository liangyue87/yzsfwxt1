package com.ljx.hfgsjt.yj.controller;

import com.ljx.hfgsjt.entity.yj.XmLd;
import com.ljx.hfgsjt.yj.service.LdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ant on 2019/5/9.
 */
@RestController
@RequestMapping(value = "LdController")
@Api(description = "楼栋管理")
public class LdController {
    @Autowired
    private LdService service;

    @ApiOperation(value = "获取楼栋列表集合")
    @RequestMapping(value = "getLdData",method = RequestMethod.GET)
    public String  getXhsData(String xmid,Integer page, Integer size){
        JSONObject jo = new JSONObject();
        List<XmLd> list = new ArrayList<XmLd>();
        int total = 0;
        int startRow=0;
        int endRow=0;
        try {
            startRow = (page - 1) * size + 1;
            endRow = page * size;
            total = service.getLdCount(xmid);
            if(total>0){
                list = service.getLdData(xmid,startRow,endRow);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("total",total);
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "楼栋数据回显")
    @RequestMapping(value = "LdhxData",method = RequestMethod.GET)
    public String  LdhxData(String id){
        JSONObject jo = new JSONObject();
        List<XmLd> list = new ArrayList<XmLd>();
        try {
            list = service.LdhxData(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }
    @ApiOperation(value = "楼栋点志记数据")
    @RequestMapping(value = "LddzjData",method = RequestMethod.GET)
    public String  LddzjData(String dzjid){
        List<XmLd> list = new ArrayList<XmLd>();
        JSONObject jo = new JSONObject();
        try {
            list = service.LddzjData(dzjid);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("row",list);
        return jo.toString();
    }

}
