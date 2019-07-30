package com.ljx.hfgsjt.xqsl.controller;

import com.ljx.hfgsjt.xqsl.dto.XjxqsqbDto;
import com.ljx.hfgsjt.xqsl.dto.XjxqsqbFjDto;
import com.ljx.hfgsjt.xqsl.service.XjxqsqbService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "XjxqsqbController")
public class XjxqsqbController {

    @Autowired
    private XjxqsqbService xjxqsqbService;

    //查询所有数据
    @ResponseBody
    @RequestMapping(value = "getXjxqsqb",method = RequestMethod.GET)
    public String getChgzsqbDtos(HttpServletResponse response){
        List<XjxqsqbDto> list = xjxqsqbService.getXjxqsqbDtos();
        JSONObject jo = new JSONObject();
        jo.put("XjxqsqbDtos",list);
        return jo.toString();
    }

    //新增
    @RequestMapping(value = "saveXjxqsqb",method = RequestMethod.POST)
    @ResponseBody
    public String saveXjxqsqb(XjxqsqbDto xjxqsqbDto, List<XjxqsqbFjDto> xjxqsqbFjDtos,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        return flag;
    }

    //修改
    @RequestMapping(value = "editXjxqsqb",method = RequestMethod.POST)
    @ResponseBody
    public String editXjxqsqb(XjxqsqbDto xjxqsqbDto, List<XjxqsqbFjDto> xjxqsqbFjDtos,
                           HttpServletRequest request, HttpServletResponse response){
        String flag = "ok";
        //UserEntity userEntity = (UserEntity) request.getParameterMap();
        try{
            xjxqsqbService.getXjxqsqbDtos();
        }catch (Exception e){
            flag = "err";
            e.printStackTrace();
        }
        return flag;
    }

}
