package com.ljx.hfgsjt.wtys.service;


import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskHistoryEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.*;
import com.ljx.hfgsjt.entity.yj.azlxEntity;

import java.util.List;
import java.util.Map;

public interface YSwtService {

    /**
     * 预算金额总汇
     * @param ysjezh
     * @return
     */
    List<YsYsjezh> getAllYsjezh(YsYsjezh ysjezh);
    /**
     *  领导审核信息
     * @return
     */
    List<YsLdshNewEntity> getYsLdsh(String sfsh, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size);
    /**
     * 获取全部的预算资料信息
     * @return
     */
    List<YsZlxx> getAllYsZlxx(YsZlxx ysZlxx);
    /**
     * 数据条数
     * @return
     */
    int getTotal();

    /**
     * 40001
     * 预算委托动态查询
     */
    List<XmYsztEntity> getall(String bjzt,String userid, String date1, String date2, String xmbh, String lxr, String xmdz, String azlx, String yszt, String xmmc, Integer page, Integer size);

    /**
     * 40002
     * 预算指派动态查询
     */
    List<YsVzpNewEntity> geYszptall(String xmid, String date1, String date2, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size);

    /**
     * 40003
     * 预算编制动态查询
     */
    List<YsVbzNewEntity>getYsBz(String xmid, String date1, String date2, String yszt, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size);
    /**
     * 获取项目范围
     */
    List<XmVfw> getXmVfw(String id, String zt);

    /**
     * 在预算表中存入初始化数据
     * @param ysrwzb
     */
    void setOny(YsRwzb ysrwzb);

    /**
     * 预算的附件信息存入数据库
     * @param ysfj
     */
    void setYsRwfj(YsRwfj ysfj);

    /**
     * 预算编制
     *
     */
    void setYsBz(Map map, String sbm);

    /**
     * 退回
     * 根据id删除预算表
     */
    void DeleById(YsRwzb ysrwzb);

    /**
     * 查询预算项
     * @return
     */
    List<XtglFylx> getYsx();

    /**
     * 获取预算专员信息
     * @return
     */
    List<YsYszy> getYSZY();

    /**
     * 获取预算专员的在办任务
     * @param UserId
     * @return
     */
    Long getYSZY(String UserId);

    /**
     * 通过id查询用户基本信息
     */
    List<YsYszy> getYszyById(YsYszy ysYszy);

    /**
     * 通过id查询预算人员在办的任务
     * @return
     */
    List<YsRwzb>getYszyXmById(String id);

    /**
     * 获取预算专人项目信息
     * @param xmid
     * @return
     */
    List<XmInstanceEntity> getYszyXmxxById(String xmid);

    /**
     * 通过项目ID和流程环节定义ID查找对应的流程实例
     * @param activityid
     * @param xmid
     * @return
     */
    WfRuInstEntity getWfRuInstByXmidAndPdefid(String pdefid, String activityid, String xmid);

    /**
     * 40002-40003
     * 指派预算专人
     * @param rwzb
     */
    void updataYszb(YsRwzb rwzb);

    /**
     * 预算审核通过
     */
    void updataYsShtg(Map map, int bs);

    /**
     * 通过流程实例ID和类型查找到单一任务
     * @param procInstId
     * @return
     */
    WfTaskEntity getTaskByInstId(String procInstId);

    /**
     * 预算签收
     * @param sfsh 是否审核
     * @param xmbh 项目编号
     * @param lxr 联系人
     * @param xmdz 项目地址
     * @param azlx 安装类型
     * @param i
     * @param size
     * @return
     */
    List<YsQsEntity> getYsQs(String sfsh, String xmbh, String lxr, String xmdz, String azlx, Integer page, Integer size);

    /**
     * 查询实例表id和实例内容表id
     * @param xmid
     * @return
     */
    WfRuInstContentEntity getWfRuInstAndWfRuInstContentByXmid(String xmid);

    /**
     * 查询当前实例
     * @param procInstId
     * @return
     */
    WfRuInstEntity getwfRuInstByid(String procInstId);

    /***
     * 查询当前实例内容
     * @param contentid
     * @return
     */
    WfRuInstContentEntity getWfRuInstContentEntityById(String contentid);

    /**
     * 查询预算数据
     * @param zbid
     * @return
     */
    List<YsRwcb> getYsRwcb(String zbid);

    /**
     * 查询驳回的信息
     * @return
     */
    YsBhEntity getYsBhEntity();

    /**
     * 查询流程任务表_办结中信息（获取回退原因）
     * @param procinstid
     * @return
     */
    WfTaskHistoryEntity getwfTaskHistoryEntity(String procinstid);

    /**
     * 更新流程任务表_办结中信息（回退原因清空）
     * @param taskid
     */
    void updateWfTaskHistory(String taskid);

    /**
     * 更新当前实例的节点标识
     * @param procinstid
     */
    void updateWfRuInstPdefId(String pdefid, String procinstid);

    /**
     * 查询被驳回的信息
     * @param taskid
     * @return
     */
    WfTaskHistoryEntity getwfTaskHistoryEntityByTaskid(String taskid);

    /**
     * 清空任务历史记录中的回退原因
     * @param taskid
     */
    void updatewfTaskHistoryEntityByTaskid(String taskid);

    /**
     * 根据项目id查询当前实例的id
     * @param xmid
     * @return
     */
    WfRuInstEntity getWfRuInstProcinstidByContentvalue(String xmid, String pdefid);

    /**
     * 删除任务主表中的该数据
     * @param xmid
     */
    void deleteRwzbByXmid(String xmid);

    /**
     * 获取任务主表的id
     * @param xmid
     * @return
     */
    YsRwzb getYsRwzbByXmid(String xmid);

    /**
     * 删除预算编制附件
     * @param fjid
     */
    void deleteFkFj(String fjid);

    /**
     * 获取预算说明（预算人员备注）
     * 预算编制
     * @return
     */
    List<YsRwzb> getYssm(String zbid);

    /**
     * 查询安装类型下拉框
     * @return
     */
    List<azlxEntity> findAzlxBySllx(Map<String, Object> pd);

    /**
     * 获取设计审图主表id
     * @param xmid
     * @param activityId
     * @return
     */
    List<WfTaskHistoryEntity> getsjstrzwid(String xmid, String activityId);

    /**
     * 更新签收状态
     * @param xmid
     * @param activityId
     */
    void updateQszt(String xmid, String activityId);

    /**
     * 获取预算委托上传的附件（列表）
     * @param xmid
     * @return
     */
    List<YsZlxx> getYszl(String xmid);

    /**
     * 保存上传用户确认凭证
     * @param ysRwyhqrfjEntity
     */
    void saveysRwyhqrfjEntity(YsRwyhqrfjEntity ysRwyhqrfjEntity);

    /**
     * 获取预算委托时上传附件
     * 用户确认上传凭证
     * @param ysrwzbid
     * @return
     */
    List<YsZlxx> getYszlYhpz(String ysrwzbid);

    /**
     * 删除附件
     * 用户确认凭证
     * @param yhqrfjid
     * @return
     */
    void deleteYhqrfj(String yhqrfjid);

    /**
     * 更新附件任务主表id
     * @param rwfjid
     * @param id
     */
    void updateYszl(String rwfjid, String id);


    List<YsZlxx> getYsZlxx(String xmid, String zbid);

    /**
     * 查询基本信息
     * @param xmid
     * @return
     */
    List<XmInstanceEntity> getYsbc(String xmid);

    /**
     * 初始化前期预算资料
     * @param xmid
     * @param zbid
     * @return
     */
    List<YsZlxx> getEarly_stage(String xmid, String zbid);

    /**
     * 初始化后期预算成果资料
     * @param zbid
     * @return
     */
    List<YsZlxx> getLate(String zbid);

    /**
     * 获取项目负责人
     * @param xmid
     * @return
     */
    XmInstanceEntity getXmfzr(String xmid);

    /**
     * 获取用户id和号码
     * @param s
     * @return
     */
    UserEntity getUserIdAndUserMobilephone(String s);

    /**
     * 待发送短信保存到数据库
     * @param dxDfsEntity
     */
    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    /**
     * 更据项目负责人查询用户信息
     * @param xmfzr
     * @return
     */
    UserEntity getUserByXmfzr(String xmfzr);

    /**
     * 会审结果基本信息
     * @param xmid
     * @return
     */
    List<Map<Object,String>> gethsjljbxx(String xmid);

    /**
     * 保存预算主表
     * @param rwzb
     */
    void saveYsRwzb(YsRwzb rwzb);

    List<Map<String, Object>> getZhcxHhldcx(ZhcxHhldcxEntity hhldcxEntity);

    int getZhcxHhldcxToTal(ZhcxHhldcxEntity hhldcxEntity);

    List<Map<String, Object>> getYsbzcxList(ZhcxYsbzEntity zhcxYsbzEntity);

    int getYsbzcxListToTal(ZhcxYsbzEntity zhcxYsbzEntity);

    YsVbzNewEntity getXxzl(String xmid);
}
