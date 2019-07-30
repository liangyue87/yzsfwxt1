package com.ljx.hfgsjt.js.mapper;

import com.ljx.hfgsjt.entity.dto.Activity.WfActivityRoleEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstContentEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwcb;
import com.ljx.hfgsjt.entity.dto.yswt.YsRwzb;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JssqMapper {

    int selectJssqCount(Jssq jssq);//列表数量

    List<Jssq> selectJssqList(Jssq jssq);//数据集合

    ys_rucb selectYshz(@Param("flbh") String flbh,@Param("xmid") String xmid);//

    int selectBzyhjssCount(Jssq jssq);//列表数量

    List<Jssq> selectBzyhjssList(Jssq jssq);//数据集合

    int selectJszlCount(String xmid);//列表数量

    List<JsRwFj> selectJszlList(Map<String, Object> map);//数据集合

    void insertJszl (JsRwFj jsRwFj);
    void insertRwzb(JsRwZb jsRwZb);
    JsRwZb selectRwzb(String xmid);
    void updataRwzb(JsRwZb jsRwZb);
    JsRwCb selectRwcb(@Param("zbid") String zbid, @Param("jsfl") String jsfl);
    void updatRwcb(JsRwCb jsRwCb);



    void delectRwfj(String id);
    void delectRwZb(String xmid);
    @Select(" delete  from  YHJS_RWCB  where yhjsrwzbid=#{zbid}")
    void delectRwCb(@Param("zbid") String zbid);

    void saveJszb(JsRwZb jsRwZb);

    void insertJsRwcb (JsRwCb jsRwCb);

    List<JsRwFj> selectJsFjList(String xmid);//列表数量

    void updateJsFj (JsRwFj jsRwFj);

    void updataInst(InstEntity instEntity);
    void  updataTask(TaskEntity taskEntity);
    InstContentEntity getRuInstContent(@Param("pdef_id") String pdef_id, @Param("xmid") String xmid);
    InstEntity getRuInst(String xmid);
    TaskEntity getTask(String xmid);

    void insertTask(TaskEntity taskEntity);
    void insertHiTask(TaskEntity taskEntity);
    void insetHiinstContent(InstContentEntity instContentEntity);
    void insertHiInst(InstEntity instEntity);

    void deleteRuInstContent(String prodid);
    void deleteRuInst(String prodid);
    void deleteTask(String prodid);

    @Select("select b.*,b.ysje_old ysjeOld,x.mc from ys_rwcb b left join XTGL_FYLX x on b.ysfl = x.bh where b.ysrwzbid = #{zbid}")
    List<YsRwcb> findAll(@Param("zbid") String zbid);


    @Select("select b.*,x.mc from YHJS_RWCB b left join XTGL_FYLX x on b.jsfl = x.bh where b.yhjsrwzbid = #{zbid}")
    List<JsRwCb> GetJsmc(@Param("zbid") String zbid);


    @Select("select b.* from YS_RWZB b where b.xmid=#{xmid}")
    YsRwzb selectYszbid (@Param("xmid") String xmid);

    //结算默认人
    List<XmUserEntity> getJsmrr(String pdefid);

    @Select("select * from XM_INSTANCE where ID = #{xmid}")
    XmInstanceEntity getXmInstanceByXmid(@Param("xmid") String xmid);

    @Select("select * from WF_ACTIVITY_ROLE where ACTIVITYID = #{activity}")
    WfActivityRoleEntity getWfActivityRoleByActivity(@Param("activity") String activity);

    @Select("select * from XTGL_USER r\n" +
            "left join XTGL_USERROLE e on r.USERID = e.USERID\n" +
            "left join WF_ACTIVITY_ROLE l on e.ROLEID = l.ROLEID\n" +
            "where l.ACTIVITYID = #{activityid}")
    UserEntity getUserByActivityid(@Param("activityid") String activityid);
}

