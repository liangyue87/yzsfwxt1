package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.Activity.WfReNodeattrEntity;
import com.ljx.hfgsjt.main.service.WfReNodeattrService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "WfReNodeattrController")
public class WfReNodeattrController {

    @Autowired
    private WfReNodeattrService service;

    @RequestMapping(value = "getWfReNodeattr",method = RequestMethod.GET)
    @ResponseBody
    public String getWfReNodeattr(int page,int size, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<WfReNodeattrEntity> nodepage = service.findAll(pageRequest);
        jo.put("total",nodepage.getTotalElements());
        jo.put("row",nodepage.getContent());
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteNodeattrByKey", method = RequestMethod.POST)
    @ResponseBody
    public String deleteNodeattrByKey(String id, HttpServletResponse response) {
        String flag = "err";
        try {
            service.deleteById(id);
        } catch (Exception e) {
            flag = "ok";
            e.printStackTrace();
        }
        return flag;
    }

    //新增 or 保存
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    public String save(WfReNodeattrEntity nodeattrEntity, HttpServletRequest request, HttpServletResponse response) {
        String flag = "ok";
        try {
            if(nodeattrEntity.getNodekey() == null || nodeattrEntity.getNodekey().equals("")){
                nodeattrEntity.setNodekey(GuidUtils.getGuid());
            }
            service.save(nodeattrEntity);
        } catch (Exception e) {
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }


}
