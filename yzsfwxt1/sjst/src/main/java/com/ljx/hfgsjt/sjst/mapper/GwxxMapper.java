package com.ljx.hfgsjt.sjst.mapper;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.zhys.XmUserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface GwxxMapper {
    int getGwxxCount(NewsGwxx gwxx);
    List<NewsGwxx> getGwxxList(NewsGwxx gwxx);
    XmInstance getXzqyDz(String xmid);
    XzqyEntity getGssName(String qybh);
    XmInstance selectXminstance(String xmid);
   int updateXminstance(XmInstance xmInstance);
   void insertGwxxZb(GwxxdcZb gwxxdcZb);
   //会审结果录入
   void insertHsjglrFj(HsjglrFj hsjglrFj);
    int getHsjgCount(String xmid );
    List<HsjglrFj> getHsjgLsit(Map<String, Object> map);
   void  delectHsfj(String id);
    List<HsjglrFj>selectHsFj(String fjlx);
    void updataHsFj(HsjglrFj hsjglrFj);

    void insertHsiglr(HsjglrZb hsjglrZb);

    @Select("select t.id, t.sjstrwid, t.xmjlyj,t.cyry,t.cjsj,to_char(t.cjsj,'yyyy-mm-dd hh24:mi') cjsj_str,t.cjr,t.xmid from SJST_SJTHSZB t where t.xmid=#{xmid}")
    HsjglrZb getHsjglr(@Param("xmid")String xmid);

    int getHsjglrCount(NewsGwxx gwxx);
    List<NewsGwxx> getHsjglrList(NewsGwxx gwxx);

    //反馈列
    int getGwxxFqCount(NewsGwxx gwxx);
    List<NewsGwxx> getGwxxFqList(NewsGwxx gwxx);

    List<GwxxdcCb> selectGwxxdcCBList(String xmid);
    GwxxdcZb selectGwxxdcZB(String xmid);
    void saveGwxxFq(GwxxdcCb gwxxdcCb);//保存官网区所反馈信息
    void deleteGwxxFq(String id);//删除区所官网反馈记录


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

    InstEntity selectRuInst(@Param("pdef_id") String pdef_id, @Param("xmid") String xmid);
    TaskEntity selectTask(@Param("pdef_id") String pdef_id, @Param("xmid") String xmid);
    //查询存储过程的方法
    String texuChange(Map<String, Object> map);

    //查询官网默认人
    XmUserEntity getGwmrr(String pdefid);

    @Select("select * from XM_INSTANCE where ID = #{xmid}")
    XmInstanceEntity getXmInstanceByXmid(@Param("xmid") String xmid);

    @Select("select r.* from XTGL_ROLE e\n" +
            "left join XTGL_USERROLE l on e.ROLEID = l.ROLEID\n" +
            "left join XTGL_USER r on l.USERID = r.USERID\n" +
            "where e.ROLETYPE = #{qs} and r.userid is not null and e.NOTE = '区所分派人'")
    UserEntity getUserByqs(@Param("qs") String qs);

    @Select("select * from WF_RU_INST_CONTENT t\n" +
            "left join WF_RU_INST s on t.PROC_INST_ID = s.PROC_INST_ID\n" +
            "where t.CONTENTVALUE = #{xmid} and t.CONTENTTYPE = 'GwxxdcZb'")
    WfRuInstEntity getWfRuInstByXmid(@Param("xmid") String xmid);

    @Select("select * from XTGL_USER r   where USERID = #{xmfzr}")
    UserEntity getUserByUserid(@Param("xmfzr") String xmfzr);

 //短信接收
 int getdxjsCount(Dx_dypz_lb dxDypzLb);
 List<Dx_dypz_lb> getdxjsList(Dx_dypz_lb dxDypzLb);

 List<Dx_dypz_lb>getUserDy(String userid);

 void insertDydx(Dx_dypz_lb dxDypzLb);
 void deleteUserDy(@Param("ywlx") String ywlx,@Param("dxlx") String dxlx,@Param("activityid") String activityid,@Param("userid") String userid);

 UserEntity getDquser(String userid);

}
