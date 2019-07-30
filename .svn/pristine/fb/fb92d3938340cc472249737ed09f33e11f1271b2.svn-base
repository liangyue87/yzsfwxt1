package com.ljx.hfgsjt.wtys.service;


import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtcbEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtzbEntity;

import java.util.List;

public interface YsZjqtService {

    public List<UserEntity> getQtcyry(String xmid);

    public String saveYszjqt(YsZjqtzbEntity ysZjqtzbEntity);

    void saveWfRuInst(WfRuInstEntity e);//流程实例
    void saveWfTaskHistory(WfTaskHistoryEntity e);//保存流程历史任务
    void saveWfTask(WfTaskEntity e);//保存流程任务
    void saveWfRuInstContent(WfRuInstContentEntity e);//保存流程需要的信息到 流程实例使用的内容表
    WfDfActivityEntity findByActivityid(String activityid);//获取当前节点信息
    List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction);//获取当前流程节点下一次节点信息

    int findMaxIdToWfRuInst();//
    int findMaxIdToWfTask();//

    void saveYszjqtcb(YsZjqtcbEntity ysZjqtcbEntity);

    /**
     * 保存短信到待发送表中
     * @param dxDfsEntity
     */
    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);
}
