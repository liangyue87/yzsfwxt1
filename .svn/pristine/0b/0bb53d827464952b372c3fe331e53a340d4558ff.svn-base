package com.ljx.hfgsjt.js.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstContentEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwcb;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwzb;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;

import java.util.List;
import java.util.Map;

public interface JssqService {
    int selectJssqCount(Jssq jssq);//列表数量

    List<Jssq> selectJssqList(Jssq jssq);//数据集合

    ys_rucb selectYshz(String flbh ,String xmid);//

    int selectBzyhjssCount(Jssq jssq);//列表数量

    List<Jssq> selectBzyhjssList(Jssq jssq);//数据集合

    int selectJszlCount(String xmid);//列表数量

    List<JsRwFj> selectJszlList(Map<String, Object> map);//数据集合

    void insertJszl (JsRwFj jsRwFj);
    void insertRwzb(JsRwZb jsRwZb);
    JsRwZb selectRwzb(String xmid);
    void updataRwzb(JsRwZb jsRwZb);
    JsRwCb selectRwcb( String zbid, String jsfl);
    void updatRwcb(JsRwCb jsRwCb);

    void delectRwfj(String id);
    void delectRwZb(String xmid);

   // void saveJszb(JsRwZb jsRwZb);
    void insertJsRwcb (JsRwCb jsRwCb);

    List<JsRwFj> selectJsFjList(String xmid);//列表数量
    void updateJsFj (JsRwFj jsRwFj);

    void updataInst(InstEntity instEntity);
    void  updataTask(TaskEntity taskEntity);
    InstContentEntity getRuInstContent( String pdef_id,  String xmid);
    InstEntity getRuInst(String xmid);
    TaskEntity getTask( String xmid);

    void insertTask(TaskEntity taskEntity);
    void insertHiTask(TaskEntity taskEntity);
    void insetHiinstContent(InstContentEntity instContentEntity);
    void insertHiInst(InstEntity instEntity);
    void deleteRuInstContent(String prodid);
    void deleteRuInst(String prodid);
    void deleteTask(String prodid);


    YsRwzb selectYszbid(String xmid);
    List<YsRwcb> getYsRwcb(String zbid);
    List<JsRwCb> GetJsmc(String zbid);


    void setYsBz(Map map);
    void delectRwCb(String zbid);

    //结算默认人
   List<XmUserEntity> getJsmrr(String pdefid);

    XmInstanceEntity getXmInstanceByXmid(String xmid);

    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    /**
     * 根据Activity查询WfActivityRole
     * @param Activity
     * @return
     */
    WfActivityRoleEntity getWfActivityRoleByActivity(String Activity);

    /**
     * 根据Activityid查询用户信息
     * 只支持一个角色一个用户
     * @param Activityid
     * @return
     */
    UserEntity getUserByActivityid(String Activityid);
}
