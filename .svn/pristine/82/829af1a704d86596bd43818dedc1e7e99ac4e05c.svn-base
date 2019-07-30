package com.ljx.hfgsjt.wtys.dao;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskHistoryEntity;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.yswt.*;
import com.ljx.hfgsjt.entity.yj.azlxEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface YsWpMapper {

    @Select("select t.*,t.PROC_INST_ID procInstId,t.PDEF_ID pdefId from WF_RU_INST t WHERE pdef_id = #{pdefid} and proc_inst_id in\n" +
            "\t\t\t(\n" +
            "\t\t\t\tselect proc_inst_id from WF_RU_INST_CONTENT where table_name in ('XM_INSTANCE')\n" +
            "\t\t\t\t                                              and contentvalue = #{xmid}\n" +
            "\t\t\t)")
    WfRuInstEntity getWfRuInstByXmidAndPdefid(@Param("pdefid") String pdefid,@Param("activityid") String activityid, @Param("xmid") String xmid);

    @Select("select t.*,t.PROC_INST_ID procInstId,t.C_ACTIVITYID cActivityid from WF_TASK t where proc_inst_id = #{instid}")
    WfTaskEntity getTaskByInstId(@Param("instid") String procInstId);

    @Select("select t.contentid contentid,t.proc_inst_id procInstId\n" +
            "from wf_ru_inst_content t where t.contenttype = 'yswt' and t.contentvalue = #{xmid}")
    WfRuInstContentEntity getWfRuInstAndWfRuInstContentByXmid(@Param("xmid") String xmid);

    @Select("select i.proc_inst_id procInstId,i.pdef_id pdefId,i.parammap,i.startdt,i.stopuser,i.procstatus,i.stopuser,i.stopdt \n" +
            "from wf_ru_inst i where i.proc_inst_id= #{procInstId}")
    WfRuInstEntity getwfRuInstByid(@Param("procInstId") String procInstId);

    @Select("select c.contentid,c.proc_inst_id procInstId,c.table_name tableName,c.*\n" +
            "from wf_ru_inst_content c where c.contentid = #{contentid}")
    WfRuInstContentEntity getWfRuInstContentEntityById(@Param("contentid") String contentid);

    @Select("select b.*,b.ysje_old ysjeOld,x.mc from ys_rwcb b left join XTGL_FYLX x on b.ysfl = x.bh where b.ysrwzbid = #{zbid}\n")
    List<YsRwcb> findAll(@Param("zbid") String zbid);

    @Select("select c.*,c.ysje_old ysjeOld from ys_rwcb c where c.ysrwzbid= #{zbid} and c.ysfl= #{toString}")
    YsRwcb getYsRwcbByYsrwzbidAndYsfl(@Param("zbid") String zbid, @Param("toString") String toString);

    @Update("update  ys_rwcb  set ysje = #{ysje},bz=#{bz} where id = #{id} ")
    void updateYscb(@Param("id") String id, @Param("bz") String bz, @Param("ysje") String ysje);

    @Select("select p.proc_inst_id procinstid,y.htyy,tt.id,tt.pxmbh,tt.xmbh,tt.sqlx,tt.azlx,tt.SLID,tt.SQBH,tt.XMLB,tt.XMTZF,tt.XMSWLXFL,tt.QBID,tt.STATUS,tt.SLRBH,to_char(tt.LXSJ,'yyyy-mm-dd hh24:mi:ss')lxsj,tt.XMFZR,tt.XMMC,tt.LXR,tt.LXRSJH,tt.XMDZ,tt.DWMC,tt.SLSJ,tt.ISZJ,tt.LXDH,tt.XZQYBH,tt.ZJZMJ,tt.SJFZR,tt.STFZR,tt.JLFZR,tt.ZJFZR,tt.YSFZR,tt.QSFZR,tt.SGFZR,tt.BZ,tt.BMBM_QS,tt.JHKSSJ,tt.JHJSSJ,tt.XMDZ_SHENG,tt.XMDZ_SHI,tt.XMDZ_XZQYBH,substr(p.pdef_id,0,1) FIRST,p.proc_status,p.pdef_id,'1' yszt\n" +
            "from xm_instance tt\n" +
            "     left join (\n" +
            "select t.contentvalue,a.pdef_id,'yx' proc_status,t.proc_inst_id from wf_ru_inst_content t\n" +
            "join wf_ru_inst a on t.proc_inst_id=a.proc_inst_id where a.procstatus=1 and upper(t.table_name)='XM_INSTANCE'\n" +
            "union all\n" +
            "select t.contentvalue,a.pdef_id,'wc' proc_status,t.proc_inst_id from wf_hi_inst_content t\n" +
            "join wf_hi_inst a on t.proc_inst_id=a.proc_inst_id where a.procstatus=1 and upper(t.table_name)='XM_INSTANCE') p on tt.id=p.contentvalue\n" +
            "     left join wf_task_history y on p.proc_inst_id=y.proc_inst_id\n" +
            "where p.pdef_id = '40001' and y.c_activityid = '40002' and y.htyy is not null")
    YsBhEntity getYsBhEntity();

    @Select("select o.*,o.proc_inst_id procInstId,o.c_activityid cActivityid from wf_task_history o where o.proc_inst_id= #{procinstid} and o.c_activityid='40002'\n")
    WfTaskHistoryEntity getwfTaskHistoryEntity(@Param("procinstid") String procinstid);

    @Update("update wf_task_history th set th.htyy='' where th.taskid= #{taskid}\n")
    void updateWfTaskHistory(@Param("taskid") String taskid);

    @Update("update wf_ru_inst st set st.pdef_id=#{pdefid} where st.proc_inst_id= #{procinstid}")
    void updateWfRuInstPdefId(@Param("pdefid") String pdefid,@Param("procinstid") String procinstid);

    @Delete("delete from ys_rwcb b where b.ysrwzbid= #{zbid}")
    void deleteYsRwcbByYsrwzbid(@Param("zbid") String zbid);

    @Select("select * from wf_task_history oo where oo.taskid= #{taskid}")
    WfTaskHistoryEntity getwfTaskHistoryEntityByTaskid(@Param("taskid") String taskid);

    @Update("update wf_task_history oo set oo.htyy='' where oo.taskid= #{taskid}")
    void updatewfTaskHistoryEntityByTaskid(@Param("taskid") String taskid);

    @Select(" select oo.proc_inst_id procinstid  from wf_ru_inst_content o\n" +
            " left join wf_ru_inst oo on o.proc_inst_id=oo.proc_inst_id where o.contentvalue= #{xmid} and oo.pdef_id= #{pdefid}")
    WfRuInstEntity getWfRuInstProcinstidByContentvalue(@Param("xmid") String xmid,@Param("pdefid") String pdefid);

    @Delete("delete from YS_RWZB b where b.xmid= #{xmid}")
    void deleteRwzbByXmid(@Param("xmid") String xmid);

    @Select("select * from ys_rwzb where xmid= #{xmid}")
    YsRwzb getYsRwzbByXmid(@Param("xmid") String xmid);

    @Delete("delete from YS_RWFJ b where b.id= #{fjid}")
    void deleteFkFj(@Param("fjid")String fjid);

    @Select("select b.* from ys_rwzb b where b.id = #{zbid}")
    List<YsRwzb> getYssm(@Param("zbid")String zbid);

    @Select("select t.codename value,t.codename label from xtgl_code t where\n" +
            "            t.parentid = (select id from xtgl_code  where\n" +
            "            parentid = (select id from xtgl_code  where codename = '安装类型') and codename = #{label})")
    List<azlxEntity> findAzlxBySllx(Map<String, Object> pd);

    @Select("select t.TASKID,t.PTASKID,t.PROC_INST_ID procInstId,t.c_activityid cActivityid,t.iscurrent,t.czr,t.qssj,t.jssj,t.czsm,t.ischeck,t.check_time check_time,\n" +
            "      t.check_memo checkMemo,t.jssx,t.jssj_memo jssjMemo,t.check_user checkUser,t.js_user jsUser,t.check_sx ,t.yssm,t.hasnew,t.slmx,t.jssx_yl ,t.refkey,t.refid\n" +
            "  from WF_TASK_HISTORY t\n" +
            " where t.proc_inst_id in\n" +
            "       (select d.proc_inst_id\n" +
            "          from WF_RU_INST_CONTENT d\n" +
            "         where lower(d.table_name) = 'xm_instance'\n" +
            "           and lower(d.contentname) = 'id'\n" +
            "           and d.contentvalue = #{xmid}\n" +
            "           \n" +
            "           union all \n" +
            "        \n" +
            "           select d.proc_inst_id\n" +
            "          from wf_hi_inst_content d\n" +
            "         where lower(d.table_name) = 'xm_instance'\n" +
            "           and lower(d.contentname) = 'id'\n" +
            "           and d.contentvalue = #{xmid}\n" +
            "           \n" +
            "           )\n" +
            "   and t.c_activityid = #{activityId}")
    List<WfTaskHistoryEntity> getsjstrzwid(@Param("xmid")String xmid, @Param("activityId")String activityId);

    @Update("update WF_TASK s set s.ISCHECK = '1' where s.TASKID = (select k.TASKID from WF_TASK k where k.C_ACTIVITYID = #{activityId}\n" +
            "                          and k.PROC_INST_ID in(\n" +
            "                              select d.PROC_INST_ID\n" +
            "                              from WF_RU_INST d\n" +
            "                              left join  WF_RU_INST_CONTENT t on d.PROC_INST_ID=t.PROC_INST_ID\n" +
            "                              where t.CONTENTVALUE = #{xmid}\n" +
            "        ))")
    void updateQszt(@Param("xmid")String xmid, @Param("activityId")String activityId);

    @Select("select j.*,u.*,j.id rwfjid  \n" +
            "from ys_rwfj j \n" +
            "left join XTGL_USER u on j.scr = u.userid\n" +
            "where j.xmid = #{xmid} ")
    List<YsZlxx> getYszl(@Param("xmid")String xmid);

    @Select("select j.*,u.*,j.id yhqrfjid\n" +
            "            from YS_RWYHQRFJ j\n" +
            "            left join XTGL_USER u on j.scr = u.userid\n" +
            "            where j.ysrwzbid = #{ysrwzbid}")
    List<YsZlxx> getYszlYhpz(@Param("ysrwzbid")String ysrwzbid);

    @Delete(" delete from YS_RWYHQRFJ b where b.id= #{yhqrfjid}")
    void deleteYhqrfj(@Param("yhqrfjid")String yhqrfjid);

    @Update("update ys_rwfj y set y.ysrwid = #{id} where y.id=#{rwfjid}")
    void updateYszl(@Param("rwfjid")String rwfjid, @Param("id")String id);

    @Select("select B.XMID, a.ID FJID,b.ID YSZBID,c.USERID,a.FJMC,a.FJHZ,to_char(a.SCSJ,'yyyy-mm-dd hh24:mi:ss') SCSJ, c.USERNAME,a.FJLJ\n" +
            "from ys_rwzb b\n" +
            "     left join ys_rwfj a on b.id=a.ysrwid\n" +
            "     left join XTGL_USER c  on c.USERID in a.SCR \n" +
            "     where b.xmid = #{xmid} and b.id= #{zbid}")
    List<YsZlxx> getYsZlxx(@Param("xmid")String xmid, @Param("zbid")String zbid);

    @Select("select e.*,d.CODENAME azlxname from xm_instance e left join XTGL_CODE d on e.AZLX = d.CODEVALUE where e.id = #{xmid}")
    List<XmInstanceEntity> getYsbc(@Param("xmid")String xmid);

    @Select("select B.XMID, a.ID FJID,b.ID YSZBID,c.USERID,a.FJMC,a.FJHZ,to_char(a.SCSJ,'yyyy-mm-dd hh24:mi:ss') SCSJ, c.USERNAME,a.FJLJ\n" +
            "            from ys_rwzb b\n" +
            "                 left join ys_rwfj a on b.xmid=a.xmid\n" +
            "                left join XTGL_USER c  on c.USERID in a.SCR\n" +
            "                 where a.xmid = #{xmid} and a.fjlx_yj = 'Early_stage'")
    List<YsZlxx> getEarly_stage(@Param("xmid")String xmid, @Param("zbid")String zbid);

    @Select("select B.XMID, a.ID FJID,b.ID YSZBID,c.USERID,a.FJMC,a.FJHZ,to_char(a.SCSJ,'yyyy-mm-dd hh24:mi:ss') SCSJ, c.USERNAME,a.FJLJ\n" +
            "from ys_rwzb b\n" +
            "     left join ys_rwfj a on b.id=a.ysrwid\n" +
            "     left join XTGL_USER c  on c.USERID in a.SCR\n" +
            "     where a.ysrwid = #{zbid} and a.fjlx_ej = 'Late'")
    List<YsZlxx> getLate(@Param("zbid")String zbid);

    @Select("select * from XM_INSTANCE where ID = #{xmid}")
    XmInstanceEntity getXmfzr(@Param("xmid") String xmid);

    @Select("select * from(\n" +
            "            select u.* from xtgl_user u where userid in\n" +
            "                 (select userid from xtgl_userrole where roleid =\n" +
            "                      (select roleid from wf_activity_role where activityid = #{s})) and rownum = '1' )")
    UserEntity getUserIdAndUserMobilephone(@Param("s") String s);

    @Select("select * from XTGL_USER u where u.USERID = #{xmfzr}")
    UserEntity getUserByXmfzr(@Param("xmfzr") String xmfzr);

    @Select("select b.*,to_char(b.cjsj,'yyyy-MM-dd hh24:mi:ss') cjsj_str from SJST_SJTHSZB b where b.XMID = #{xmid}")
    List<Map<Object,String>> gethsjljbxx(@Param("xmid") String xmid);

    @Select("<script>" +
            "select * from(" +
            " select s.*,rownum rn from (select * from zhcx_hhldcx " +
            "where 1=1 " +
            "<when test=\"kssj != null and kssj != ''\">"+
            "and jssx_yl &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and jssx_yl &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"activityname != null and activityname != ''\">"+
            "and substr(c_activityid,0,1) = #{activityname}"+
            "</when>"+
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and rwblr like concat(concat('%',#{rwblr}),'%')"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 0 \">"+
            "and ld = '0' and hd = '0'"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 1 \">"+
            "and ld = '1' and hd = '0'"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 2 \">"+
            "and ld = '1' and hd = '1'"+
            "</when>"+
            "order by xmbh desc) s " +
            "where rownum &lt;= #{endRow}" +
            ") where rn &gt;= #{startRow}" +
            "</script>")
    List<Map<String, Object>> getZhcxHhldcx(ZhcxHhldcxEntity hhldcxEntity);

    @Select("<script>" +
            "select count(1) from zhcx_hhldcx where 1=1" +
            "<when test=\"kssj != null and kssj != ''\">"+
            "and jssx_yl &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and jssx_yl &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"activityname != null and activityname != ''\">"+
            "and substr(c_activityid,0,1) = #{activityname}"+
            "</when>"+
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and rwblr like concat(concat('%',#{rwblr}),'%')"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 0 \">"+
            "and ld = '0' and hd = '0'"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 1 \">"+
            "and ld = '1' and hd = '0'"+
            "</when>"+
            "<when test=\"d != null and d != '' and d == 2 \">"+
            "and ld = '1' and hd = '1'"+
            "</when>"+
            "</script>")
    int getZhcxHhldcxToTal(ZhcxHhldcxEntity hhldcxEntity);

    @Select("<script>" +
            "select * from(" +
            " select s.*,rownum rn from (select * from zhcx_ysbz " +
            "where 1=1 " +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"codename != null and codename != ''\">"+
            "and codename = #{codename}"+
            "</when>"+
            "<when test=\"ysr != null and ysr != ''\">"+
            "and ysr like concat(concat('%',#{ysr}),'%')"+
            "</when>"+
            "order by xmbh desc) s " +
            "where rownum &lt;= #{endRow}" +
            ") where rn &gt;= #{startRow}" +
            "</script>")
    List<Map<String, Object>> getYsbzcxList(ZhcxYsbzEntity zhcxYsbzEntity);

    @Select("<script>" +
            "select count(1) from zhcx_ysbz where 1=1" +
            "<when test=\"xmmc != null and xmmc != ''\">"+
            "and xmmc like concat(concat('%',#{xmmc}),'%')"+
            "</when>"+
            "<when test=\"xmbh != null and xmbh != ''\">"+
            "and xmbh like concat(concat('%',#{xmbh}),'%')"+
            "</when>"+
            "<when test=\"xmfzr != null and xmfzr != ''\">"+
            "and xmfzr like concat(concat('%',#{xmfzr}),'%')"+
            "</when>"+
            "<when test=\"codename != null and codename != ''\">"+
            "and codename = #{codename}"+
            "</when>"+
            "<when test=\"ysr != null and ysr != ''\">"+
            "and ysr like concat(concat('%',#{ysr}),'%')"+
            "</when>"+
            "</script>")
    int getYsbzcxListToTal(ZhcxYsbzEntity zhcxYsbzEntity);

    @Select("  select distinct zb.ID zbid,xm.*,t.PROC_INST_ID procinstid\n" +
            "  from XM_INSTANCE xm\n" +
            "left join YS_RWZB zb on xm.ID = zb.XMID\n" +
            "left join GET_ALL_CONTENT t on zb.XMID = t.CONTENTVALUE\n" +
            "  left join WF_TASK_HISTORY y on t.PROC_INST_ID = y.PROC_INST_ID\n" +
            "\n" +
            "where xm.ID = #{xmid} and y.C_ACTIVITYID = '40003'")
    YsVbzNewEntity getXxzl(@Param("xmid") String xmid);
}
