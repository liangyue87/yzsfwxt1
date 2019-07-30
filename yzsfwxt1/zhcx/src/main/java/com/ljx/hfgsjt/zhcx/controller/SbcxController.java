package com.ljx.hfgsjt.zhcx.controller;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
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
import java.util.Map;

@RestController
@RequestMapping(value = "SbcxController")
@Api(description = "水表查询")
public class SbcxController {
    @Autowired
    private ZhysService zhysService;



    @ApiOperation(value = "获取水表列表")
    @RequestMapping(value = "getSbData", method = RequestMethod.GET)
    public String getSbData(BaseEntity baseEntity, HttpServletRequest request) {
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        int total = 0;
        try {
            list = zhysService.getSbxxList(baseEntity);
            total = zhysService.getSbxxTotal(baseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jo.put("total", total);
        jo.put("list", list);
        return jo.toString();

    }



}
