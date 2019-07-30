package com.ljx.hfgsjt.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.main.service.WorkService;
import com.ljx.hfgsjt.util.utils.DateUtils;
import com.ljx.hfgsjt.util.utils.StringUtil;
import com.ljx.hfgsjt.xkybz.service.XkylxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "WorkController")
@Api(description = "我的工作")
public class WorkController {

    @Autowired
    private WorkService service;
    @Autowired
    private XkylxService xkylxService;

    //获取任务类别
    @RequestMapping(value = "getTypeCheckbox",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取任务类别")
    public String getTypeCheckbox(HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,String>> list = service.getTypeCheckbox();
        jo.put("list",list);
        return jo.toString();
    }


    //获取待办任务
    @RequestMapping(value = "findBacklog",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取我的待办任务")
    public String findBacklog(BaseEntity baseEntity,HttpServletRequest request, HttpServletResponse response){
        //HttpSession session = request.getSession();
        //baseEntity.setRwblr("");
        baseEntity.setEndRow();
        baseEntity.setStartRow();
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = null;
        int total = 0;
        try{
            list = service.getMyBacklog(baseEntity);
            total = service.getMyBacklogTotal(baseEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("list",list);
        jo.put("total",total);
        return jo.toString();
    }

    //获取办理过的任务
    @RequestMapping(value = "findDone",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取办理过的任务")
    public String findDone(BaseEntity baseEntity,HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        baseEntity.setEndRow();
        baseEntity.setStartRow();
        List<Map<String,Object>> list = null;
        int total = 0;
        try{
            list = service.getMyDone(baseEntity);
            total = service.getMyDoneTotal(baseEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("list",list);
        jo.put("total",total);
        return jo.toString();
    }

    //获取我发起的任务
    @RequestMapping(value = "findOriginate",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取我发起的任务")
    public String findOriginate(BaseEntity baseEntity,HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        baseEntity.setEndRow();
        baseEntity.setStartRow();
        List<Map<String,Object>> list = null;
        int total = 0;
        try{
            list = service.getMyOriginate(baseEntity);
            total = service.getMyOriginateTotal(baseEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        jo.put("list",list);
        jo.put("total",total);
        return jo.toString();
    }

    //获取项目经理首页数据
    @RequestMapping(value = "getData",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取项目经理首页数据")
    public String getData(String userid,HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> data = service.getXmData(userid);
        Map<String,Object> hhl = service.getHhldData(userid);
        for(Map map : data){
            jo.put(map.get("CODE").toString(),map.get("NUM"));
        }
        jo.put("hhl",hhl);
        return jo.toString();
    }



    //获取项目制定环节的任务办理完结情况
    @RequestMapping(value = "getAllInstInfo",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取项目制定环节的任务办理完结情况")
    public String getAllInstInfo(String xmid,String c_activityid,HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            if(c_activityid.equals("1")){
                XmInstanceEntity xm = xkylxService.getXmxxById(xmid);
                if(xm != null){
                    list = service.getAllInstInfo(xm.getQbid(),c_activityid);
                }else {
                    list = service.getAllInstInfo(xmid,c_activityid);
                }
            }else if(c_activityid.equals("2")){
                XmInstanceEntity xm = xkylxService.getXmxxById(xmid);
                List<Map<String,Object>> list1 = null;
                List<Map<String,Object>> list2 = null;
                if(xm != null){
                    list1 = service.getAllInstInfo(xm.getQbid(),c_activityid);
                }
                list2 = service.getAllInstInfo(xmid,c_activityid);
                if(list1 != null){
                    list.addAll(list1);
                }
                if(list2 != null){
                    list.addAll(list2);
                }
            }else {
                list = service.getAllInstInfo(xmid,c_activityid);
            }
            jo.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }


    //获取项目制定环节的任务办理完结情况
    @RequestMapping(value = "getAllInstInfoNum",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取项目制定环节的任务办理完结情况")
    public String getAllInstInfoNum(String xmid,HttpServletRequest request, HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        try {
            XmInstanceEntity xm = xkylxService.getXmxxById(xmid);
            if(xm != null){
                List<Map<String,Object>> list1 = null;
                List<Map<String,Object>> list2 = null;
                list1 = service.getAllInstInfoNum(xm.getQbid());
                list2 = service.getAllInstInfoNum(xmid);
                list.addAll(list1);
                list.addAll(list2);
            }else {
                list = service.getAllInstInfoNum(xmid);
            }
            jo.put("list",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jo.toString();
    }

    //获取项目经理首页消息提醒
    @RequestMapping(value = "getMessageList",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取项目经理首页消息提醒")
    public String getMessageList(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        boolean isGreen = false;
        boolean isYellow = false;
        boolean isRed = false;
        UserEntity user = (UserEntity)request.getSession().getAttribute("user");
        List<Map<String,Object>> data = service.getMessageList(user.getUserid());
        for (Map<String,Object> map:data

             ) {
            isGreen = false;
            isYellow = false;
            isRed = false;
            if(StringUtil.isNotEmpty(map.get("JSSX"))&&StringUtil.isNotEmpty(map.get("JSSJ"))&&StringUtil.isNotEmpty(map.get("JSSX_YL"))){
                if(DateUtils.stringToDate(map.get("JSSX").toString(),"yyyy-MM-dd HH:mm").after(DateUtils.stringToDate(map.get("JSSJ").toString(),"yyyy-MM-dd HH:mm"))){
                    isGreen = true;
                }
                if(DateUtils.stringToDate(map.get("JSSJ").toString(),"yyyy-MM-dd HH:mm").after(DateUtils.stringToDate(map.get("JSSX_YL").toString(),"yyyy-MM-dd HH:mm"))){
                    isRed = true;
                }
                if(!DateUtils.stringToDate(map.get("JSSJ").toString(),"yyyy-MM-dd HH:mm").before(DateUtils.stringToDate(map.get("JSSX").toString(),"yyyy-MM-dd HH:mm"))&&!DateUtils.stringToDate(map.get("JSSX_YL").toString(),"yyyy-MM-dd HH:mm").before(DateUtils.stringToDate(map.get("JSSJ").toString(),"yyyy-MM-dd HH:mm"))){
                    isYellow = true;
                }
            }
            map.put("isGreen",isGreen);
            map.put("isYellow",isYellow);
            map.put("isRed",isRed);
        }
        jo.put("rows",data);
        return jo.toString();
    }

}
