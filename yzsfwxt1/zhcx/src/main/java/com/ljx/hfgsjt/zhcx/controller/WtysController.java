package com.ljx.hfgsjt.zhcx.controller;


import com.ljx.hfgsjt.entity.zhcx.Wtysqk;
import com.ljx.hfgsjt.zhcx.service.WtysService;
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
@RequestMapping(value = "WtysController")
@Api(description = "委托预算情况")
public class WtysController {
    @Autowired
    private WtysService wtysService;

    @ApiOperation(value = "委托预算情况列表数据")
    @RequestMapping(value = "getWtysLsit", method = RequestMethod.GET)
    public String getWtysLsit(HttpServletRequest request,String xmmc,Integer page, Integer size,Integer wtyszt) {
        JSONObject jo = new JSONObject();
        List<Wtysqk> list = new ArrayList<Wtysqk>();
        int total = 0;
        try {
                list = wtysService.selectWtysList(xmmc,page,size,wtyszt);

        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("total", list.size());
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }




}
