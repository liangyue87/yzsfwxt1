package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.Wtysqk;
import com.ljx.hfgsjt.zhcx.service.KxlbService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "KxlbController")
@Api(description = "款项列表查询")
public class KxlbController {
    @Autowired
    private KxlbService kxlbService;



    @ApiOperation(value = "款项列表查询列表数据")
    @RequestMapping(value = "getKxlbList", method = RequestMethod.GET)
    public String getKxlbList(HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        List<Wtysqk> list = new ArrayList<Wtysqk>();
        int total = 0;
        try {
            list = kxlbService.selectKxlbList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("total", total);
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }



}
