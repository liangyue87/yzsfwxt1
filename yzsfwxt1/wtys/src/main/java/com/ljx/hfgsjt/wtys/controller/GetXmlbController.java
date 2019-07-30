package com.ljx.hfgsjt.wtys.controller;

import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.wtys.GetXmlbEntity;
import com.ljx.hfgsjt.wtys.service.GetXmlbService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "YsZjqtGetXmlb")
@CrossOrigin
public class GetXmlbController {

    @Autowired
    private GetXmlbService getXmlbService;

    private Logger logger = LoggerFactory.getLogger(GetXmlbController.class);

    //查询所有数据
    @RequestMapping(value = "getAllXqsl",method = RequestMethod.GET)
    @ResponseBody
    public String getAllXqsl(Integer page, Integer size, HttpServletResponse response, HttpServletRequest request){
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<GetXmlbEntity> pagelist = getXmlbService.getAllXmlb(pageRequest);
//        jo.put("total",pagelist.getTotalElements());
//        jo.put("row",pagelist.getContent());
        JSONObject jo = new JSONObject();
        Map<String,Object> pd = new HashMap<>();
        HttpSession session = request.getSession();
        UserEntity user =  (UserEntity)session.getAttribute("user");
        pd.put("xmfzr",user.getUserid());
        pd.put("page",page);
        pd.put("size",size);
        pd.put("lxr",request.getParameter("lxr"));
        pd.put("xmdz",request.getParameter("xmdz"));
        pd.put("xmmc",request.getParameter("xmmc"));
        pd.put("jssj",request.getParameter("jssj"));
        pd.put("kssj",request.getParameter("kssj"));
        pd.put("sfbz",request.getParameter("sfbz"));
        List<GetXmlbEntity> list = getXmlbService.getAllXqsl(pd);
        jo.put("row",list);
        int total = getXmlbService.getAllXqslList(pd).size();
        jo.put("total", total);
        return jo.toString();
    }
}
