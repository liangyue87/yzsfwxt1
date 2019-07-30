package com.ljx.hfgsjt.main.service;


import com.ljx.hfgsjt.entity.dto.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WorkService {
    List<Map<String,String>> getTypeCheckbox();

    List<Map<String,Object>> getMyBacklog(BaseEntity baseEntity);//获取我的待办任务
    int getMyBacklogTotal(BaseEntity baseEntity);//获取我的待办任务数量
    List<Map<String,Object>> getMyDone(BaseEntity baseEntity);//获取我办理的
    int getMyDoneTotal(BaseEntity baseEntity);//获取我办理的
    List<Map<String,Object>> getMyOriginate(BaseEntity baseEntity);//获取我发起的任务
    int getMyOriginateTotal(BaseEntity baseEntity);//获取我发起的任务

    //项目经理首页
    List<Map<String,Object>> getXmData(@Param("userid") String userid);//受理分派 在办项目 在收费用 数据
    Map<String,Object> getHhldData(@Param("userid") String userid);



    List<Map<String,Object>> getAllInstInfo(String xmid,String c_activityid);//项目全景图各实例办结情况
    List<Map<String,Object>> getAllInstInfoNum(@Param("xmid") String xmid);//项目全景图关于办结情况统计 红黄灯
    //获取项目经理首页消息提醒
    List<Map<String,Object>> getMessageList(String userid);
}
