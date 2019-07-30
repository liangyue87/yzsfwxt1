package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.zhcx.zhyslb;
import com.ljx.hfgsjt.zhcx.service.ZhysService;
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
@RequestMapping(value = "ZhyslbController")
@Api(description = "综合验收列表")
public class ZhysController {
    @Autowired
    private ZhysService zhysService;

    @ApiOperation(value = "综合验收列表数据")
    @RequestMapping(value = "getZhyslbList", method = RequestMethod.GET)
    public String getWtysList(HttpServletRequest request,String xmmc,Integer page, Integer size,Integer ysqk) {
        JSONObject jo = new JSONObject();
        List<zhyslb> list = new ArrayList<zhyslb>();

        try {
                list = zhysService.selectZhysList(xmmc,page,size,ysqk);

        } catch (Exception e) {
            e.printStackTrace();
        }

        jo.put("total", list.size());
        jo.put("list", list);
        System.out.println("------" + jo.toString());
        return jo.toString();

    }




}
