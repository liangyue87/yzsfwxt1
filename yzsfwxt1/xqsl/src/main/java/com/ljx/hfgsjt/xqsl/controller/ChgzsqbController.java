package com.ljx.hfgsjt.xqsl.controller;

import com.ljx.hfgsjt.xqsl.dto.ChgzsqbDto;
import com.ljx.hfgsjt.xqsl.service.ChgzsqbService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping(value = "ChgzsqbController")
public class ChgzsqbController {

    @Autowired
    private ChgzsqbService chgzsqbService;

    //查询所有数据
    @RequestMapping(value = "getChgzsqbDtos",method = RequestMethod.GET)
    @ResponseBody
    public String getChgzsqbDtos(HttpServletResponse response){
        List<ChgzsqbDto> list = chgzsqbService.getChzgsqbDtos();
        JSONObject jo = new JSONObject();
        jo.put("chgzsqbDtos",list);
        return jo.toString();
    }

}
