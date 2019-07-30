package com.ljx.hfgsjt.main.controller;


import com.alibaba.fastjson.JSONObject;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XtglWkzbEntity;
import com.ljx.hfgsjt.main.service.WorkdayService;
import com.ljx.hfgsjt.util.utils.GuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "WorkdayController")
public class WorkdayController {

    @Autowired
    private WorkdayService service;

    //获取工作日休息和休息日补班列表
    @RequestMapping(value = "getDaysList",method = RequestMethod.GET)
    @ResponseBody
    public String getBankList(HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<Map<String,Object>> list = service.getDaysList();
        jo.put("list",list);
        return jo.toString();
    }

    //保存特殊工作日
    @RequestMapping(value = "saveDays",method = RequestMethod.POST)
    @ResponseBody
    public String saveDays(String id,String day,String work,String week,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        Map map = new HashMap();
        map.put("day",day);
        map.put("work",work);
        map.put("week",week);
        String result = "success";
        try {
            if(id == null || id == ""){
                map.put("id", GuidUtils.getGuid());
                service.saveDays(map);
            }else {
                map.put("id",id);
                service.updateDays(map);
            }
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        jo.put("result",result);
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteDays",method = RequestMethod.POST)
    @ResponseBody
    public String deleteDays(String id,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        String result = "success";
        try {
            service.deleteDays(id);
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        jo.put("result",result);
        return jo.toString();
    }


    //获取上下班时间
    @RequestMapping(value = "getWorkTime",method = RequestMethod.GET)
    @ResponseBody
    public String getWorkTime(HttpServletResponse response){
        JSONObject jo = new JSONObject();
        List<XtglWkzbEntity> list = service.getWorkTime();
        jo.put("list",list);
        return jo.toString();
    }

    //保存上下班时间设置
    @RequestMapping(value = "saveTime",method = RequestMethod.POST)
    @ResponseBody
    public String saveTime(XtglWkzbEntity xtglWkzbEntity, HttpServletRequest request){
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        JSONObject jo = new JSONObject();
        String result = "success";
        HttpSession session = request.getSession();
        UserEntity user = (UserEntity)session.getAttribute("user");
        xtglWkzbEntity.setCzr(user.getUserid());
        List<XtglWkzbEntity> list = service.getWorkTime();
        try {
            if(xtglWkzbEntity.getId() == null || xtglWkzbEntity.getId() == ""){
                xtglWkzbEntity.setId(GuidUtils.getGuid());
                if(list.size() == 0){
                    xtglWkzbEntity.setJssj("2199-12-31");
                }else {
                    for(XtglWkzbEntity xl : list){
                        Date t1 = sdft.parse(xl.getKssj());
                        Date t2 = sdft.parse(xl.getJssj());
                        Date t = sdft.parse(xtglWkzbEntity.getKssj());
                        int kk = t.compareTo(t1);
                        int jj = t.compareTo(t2);
                        if( kk == 1 && (jj == -1 || jj == 0)){
                            xtglWkzbEntity.setJssj(xl.getJssj());
                            xl.setJssj(xtglWkzbEntity.getKssj());
                            service.updateTime(xl);
                            break;
                        }
                    }
                }
                service.saveTime(xtglWkzbEntity);
            }else {
                service.updateTime(xtglWkzbEntity);
            }
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        jo.put("result",result);
        return jo.toString();
    }

    //删除
    @RequestMapping(value = "deleteTime",method = RequestMethod.POST)
    @ResponseBody
    public String deleteTime(String id,HttpServletResponse response){
        JSONObject jo = new JSONObject();
        String result = "success";
        SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
        List<XtglWkzbEntity> list = service.getWorkTime();
        try {
            for(int i =0;i<list.size();i++){
                if(list.size() == 1){
                    break;
                }
                if(list.get(i).getId().equals(id) && i == list.size()-1){//删除的是最后一项
                    XtglWkzbEntity xl = list.get(i-1);
                    String jssj = "2199-12-31";
                    xl.setJssj(jssj);
                    service.updateTime(xl);
                    break;
                }else if(list.get(i).getId().equals(id) && i == 0){//删除第一项
                    XtglWkzbEntity xl = list.get(i-1);
                    String kssj = "2014-01-01";
                    xl.setKssj(kssj);
                    service.updateTime(xl);
                    break;
                }else if(list.get(i).getId().equals(id) && i != 0 && i != list.size()-1){
                    XtglWkzbEntity xl = list.get(i-1);
                    XtglWkzbEntity xh = list.get(i+1);
                    String jssj = xh.getKssj();
                    xl.setJssj(jssj);
                    service.updateTime(xl);
                    break;
                }
            }
            service.deleteTime(id);
        } catch (Exception e) {
            result = "fail";
            e.printStackTrace();
        }
        jo.put("result",result);
        return jo.toString();
    }


}
