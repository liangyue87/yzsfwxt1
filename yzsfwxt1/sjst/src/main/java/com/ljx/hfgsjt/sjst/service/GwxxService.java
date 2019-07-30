package com.ljx.hfgsjt.sjst.service;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;

import java.util.List;
import java.util.Map;

public interface GwxxService {

    int getGwxxCount(NewsGwxx gwxx);
    List<NewsGwxx> getGwxxList(NewsGwxx gwxx);
    XmInstance getXzqyDz(String xmid);
    XzqyEntity getGssName(String qybh);

    XmInstance selectXminstance(String xmid);
    int updateXminstance(XmInstance xmInstance);

    //会审结果录入
    void insertHsjglrFj(HsjglrFj hsjglrFj);
    int getHsjgCount(String xmid );
    List<HsjglrFj> getHsjgLsit(Map<String, Object> map);
    void  delectHsfj(String id);
    List<HsjglrFj>selectHsFj(String fjlx);
    void updataHsFj(HsjglrFj hsjglrFj);
    void insertHsiglr(HsjglrZb hsjglrZb);
    HsjglrZb getHsjglr(String xmid);

    //反馈列
    int getGwxxFqCount(NewsGwxx gwxx);
    List<NewsGwxx> getGwxxFqList(NewsGwxx gwxx);

    List<GwxxdcCb> selectGwxxdcCBList(String xmid);
    GwxxdcZb selectGwxxdcZB(String xmid);
    void saveGwxxFq(GwxxdcCb gwxxdcCb);//保存官网区所反馈信息....
    void deleteGwxxFq(String id);//删除区所官网反馈记录

    void insertGwxxZb(GwxxdcZb gwxxdcZb);
    int getHsjglrCount(NewsGwxx gwxx);
    List<NewsGwxx> getHsjglrList(NewsGwxx gwxx);

    //反馈实例额
    InstContentEntity getRuInstContent(String xmid);
    void insetHiinstContent(InstContentEntity instContentEntity);
    InstEntity getRuInst(String xmid);
    void insertHiInst(InstEntity instEntity);
    TaskEntity getTask(String xmid);
    void insertTask(TaskEntity taskEntity);
    void insertHiTask(TaskEntity taskEntity);
    void updataInst(InstEntity instEntity);
    void  updataTask(TaskEntity taskEntity);

    void deleteRuInstContent(String prodid);
    void deleteRuInst(String prodid);
    void deleteTask(String prodid);
    void deleteGwxxZb(String xmid);
    void deleteGwxxCb(String zbid);

    InstEntity selectRuInst( String pdef_id,  String xmid);
    TaskEntity selectTask( String pdef_id,  String xmid);
    //查询存储过程的方法
    String texuChange(Map<String, Object> map);

    XmUserEntity getGwmrr(String pdefid);

    /**
     * 根据项目id查询项目信息
     * @param xmid
     * @return
     */
    XmInstanceEntity getXmInstanceByXmid(String xmid);

    /**
     * 根据区所查找用户信息
     * @param qs
     * @return
     */
    UserEntity getUserByqs(String qs);

    /**
     * 保存信息到短信待发送
     * @param dxDfsEntity
     */
    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    /**
     * 查找流程实例表
     * @param xmid
     * @return
     */
    WfRuInstEntity getWfRuInstByXmid(String xmid);

    /**
     * 根据用户id 查找用户信息
     * @param xmfzr
     * @return
     */
    UserEntity getUserByUserid(String xmfzr);


    //短信接收
    int getdxjsCount(Dx_dypz_lb dxDypzLb);
    List<Dx_dypz_lb> getdxjsList(Dx_dypz_lb dxDypzLb);
    List<Dx_dypz_lb>getUserDy(String userid);
    void deleteUserDy(String ywlx,String dxlx,String activityid,String userid);
    UserEntity getDquser(String userid);
    void insertDydx(Dx_dypz_lb dxDypzLb);
}
