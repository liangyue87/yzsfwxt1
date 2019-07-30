package com.ljx.hfgsjt.sgjg.dao;


import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.GdModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.htqd.HtQdzbEntity;
import com.ljx.hfgsjt.entity.sgjg.GetXmlbSgEntity;
import com.ljx.hfgsjt.entity.sgjg.GetsgxtzbEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface SgxdMapper {

    @Select("select z.* from(" +
            "select x.id,x.hh,x.hm,x.dz,k.mc kj,x.mph,'wxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,''sgxdrwid," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable  from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              where x.HHLXBH = 'WJJLB' and x.zt = 0 and x.id not in" +
            "              (select distinct sbid from sgxt_rw_sb where sbid is not null)" +
            "             union all" +
            "              select x.id,x.hh,x.hm,x.dz,k.mc kj,x.mph,'dxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,k.sgxdrwid," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable" +
            "               from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.sbid,s.sgxdrwid from sgxt_rw_sb s where s.sgxdrwid not in (select id from sgxt_rwb) and s.type = 'WJJLB' and s.sgxdrwid is not null) k on x.id = k.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              where x.HHLXBH = 'WJJLB' and x.zt = 0 and k.sgxdrwid is not null" +
            "             union all" +
            "              select x.id,x.hh,x.hm,x.dz,k.mc kj,x.mph,'yxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,p.sgxdrwid," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.*,r.xmid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.type = 'WJJLB') p on x.id = p.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              where x.HHLXBH = 'WJJLB' and x.zt = 0 and x.id in" +
            "              (select distinct s.sbid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.sgxdrwid is not null and r.shzt=1)" +
            "              union all" +
            "             select x.id,x.hh,x.hm,x.dz,k.mc kj,x.mph,'dbj' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,p.sgxdrwid," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable  from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.*,r.xmid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.type = 'WJJLB') p on x.id = p.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              where x.HHLXBH = 'WJJLB' and x.zt = 0 and x.id in" +
            "              (select distinct s.sbid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.sgxdrwid is not null and r.shzt =0 or r.shzt = 2))z" +
            "              where z.xm_id=#{xmid} order by z.hh")
//    @Select("select * from " +
//            " (select s.*,rownum rn from sgxd_wb s where s.xm_id = #{xmid})" +
//            " WHERE rn > #{pagebefore} AND rn <= #{pageafter}")
    List<Map<String, Object>> queryWjjlbByXmid(Map<String, Object> pd);

    @Select("select z.* from(" +
            "select x.id,x.hh,x.hm,x.type sbType,x.dz,k.mc kj,x.mph,'wxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,''sgxdrwid,case when cd.tzdbh is null then '未缴费' else '已缴费'end jfzt," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable  from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              left join (select z.*,s.sbid from sfkp_sftzdzb z left join sfkp_sftzdcb c on z.id = c.sftzdzbid left join sfkp_sftzdcbsb s on c.id = s.sftzdcbid where z.tzdzt = 1 and s.sbid is not null) cd on cd.sbid = x.id and cd.xmid = x.xm_id " +
            "              where x.HHLXBH = 'DB' and x.zt = 0 and x.id not in" +
            "              (select distinct sbid from sgxt_rw_sb where sbid is not null)" +
            "             union all" +
            "             select x.id,x.hh,x.hm,x.dz,x.type sbType,k.mc kj,x.mph,'dxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,k.sgxdrwid,case when cd.tzdbh is null then '未缴费' else '已缴费'end jfzt," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable" +
            "               from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.sbid,s.sgxdrwid from sgxt_rw_sb s where s.sgxdrwid not in (select id from sgxt_rwb) and s.type = 'DB' and s.sgxdrwid is not null) k on x.id = k.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              left join (select z.*,s.sbid from sfkp_sftzdzb z left join sfkp_sftzdcb c on z.id = c.sftzdzbid left join sfkp_sftzdcbsb s on c.id = s.sftzdcbid where z.tzdzt = 1 and s.sbid is not null) cd on cd.sbid = x.id and cd.xmid = x.xm_id " +
            "              where x.HHLXBH = 'DB' and x.zt = 0 and k.sgxdrwid is not null" +
            "             union all" +
            "              select x.id,x.hh,x.hm,x.type sbType,x.dz,k.mc kj,x.mph,'yxd' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,p.sgxdrwid,case when cd.tzdbh is null then '未缴费' else '已缴费'end jfzt," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable" +
            "              from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.*,r.xmid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.type = 'DB') p on x.id = p.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              left join (select z.*,s.sbid from sfkp_sftzdzb z left join sfkp_sftzdcb c on z.id = c.sftzdzbid left join sfkp_sftzdcbsb s on c.id = s.sftzdcbid where z.tzdzt = 1 and s.sbid is not null) cd on cd.sbid = x.id and cd.xmid = x.xm_id " +
            "              where x.HHLXBH = 'DB' and x.zt = 0 and x.id in" +
            "              (select distinct s.sbid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.sgxdrwid is not null and r.shzt=1)" +
            "              union all" +
            "             select x.id,x.hh,x.hm,x.type sbType,x.dz,k.mc kj,x.mph,'dbj' type,h.mc hyflname,y.mc ysxzname,j.codename jffsname,x.xm_id,p.sgxdrwid,case when cd.tzdbh is null then '未缴费' else '已缴费' end jfzt," +
            "(select count(1) from sgxt_rw_sb where SBID=x.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable" +
            "              from xm_sb x" +
            "              left join xtgl_hyfl h on h.bh = x.hyflbh" +
            "              left join xtgl_ysxz y on y.bh = x.ysxzbh" +
            "              left join (select * from xtgl_code where parentid = (select id from xtgl_code where type = 'jffs')) j on j.codevalue = x.jffsbh" +
            "              left join (select s.*,r.xmid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.type = 'DB') p on x.id = p.sbid" +
            "              left join xtgl_kj k on x.kjbh = k.bh" +
            "              left join (select z.*,s.sbid from sfkp_sftzdzb z left join sfkp_sftzdcb c on z.id = c.sftzdzbid left join sfkp_sftzdcbsb s on c.id = s.sftzdcbid where z.tzdzt = 1 and s.sbid is not null) cd on cd.sbid = x.id and cd.xmid = x.xm_id " +
            "              where x.HHLXBH = 'DB' and x.zt = 0 and x.id in" +
            "              (select distinct s.sbid from sgxt_rw_sb s left join  sgxt_rwb r on s.sgxdrwid = r.id where s.sgxdrwid is not null and r.shzt =0 or r.shzt = 2))z" +
            "              where z.xm_id=#{xmid} order by z.hh")
//    @Select("select * from " +
//            " (select s.*,rownum rn from sgxd_db s where s.xm_id = #{xmid})" +
//            " WHERE rn > #{pagebefore} AND rn <= #{pageafter}")
    List<Map<String, Object>> queryDbByXmid(Map<String, Object> pd);

    @Select("select s.* from sgxd_hb s where s.xm_id = #{xmid}")
//    @Select("select * from (select s.*,rownum rn from sgxd_hb s where s.xm_id = #{xmid})" +
//            " WHERE rn > #{pagebefore} AND rn <= #{pageafter} ")
    List<Map<String, Object>> queryHbByXmid(Map<String, Object> pd);

    @Select("select s.* from sgxd_wb s where s.xm_id = #{xmid} and s.sgxdrwid = #{sgdid} order by s.hh")
    List<Map<String, Object>> queryWjjlbByXmids(Map<String, Object> pd);

    @Select("select s.* from sgxd_db s where s.xm_id = #{xmid} and s.sgxdrwid = #{sgdid} order by s.hh")
    List<Map<String, Object>> queryDbByXmids(Map<String, Object> pd);

    @Select("select s.* from sgxd_hb s where s.xm_id = #{xmid} and s.sgxdrwid = #{sgdid}")
    List<Map<String, Object>> queryHbByXmids(Map<String, Object> pd);

    @Select("select * from (select y.wlbm,y.wlmc,y.ggxh,y.wljbfl,y.jbjldw,x.sxsl xysl,ROWNUM rn from xm_wlqd x " +
            " left join xtgl_wlk y on x.wlkid = y.id where x.xmid = #{xmid}) " +
            " WHERE rn > #{pagebefore} AND rn <= #{pageafter}")
    List<Map<String, Object>> queryWlxxByXmid(Map<String, Object> pd);

    @Select("select t.bh,t.mc,x.username cjr,to_char(t.cjsj,'yyyy-MM-dd') cjsj from ${tablename} t " +
            " left join xtgl_user x on t.cjr = x.userid where t.xmid = #{xmid}")
    List<Map<String, Object>> queryXdfwJtByXmid(Map<String, Object> pd);

    @Select("select y.mc tymc,t.ldbh bh,t.ldmc mc,x.username cjr,to_char(t.cjsj,'yyyy-MM-dd') cjsj " +
            " from ${tablename} t left join xtgl_user x on t.cjr = x.userid left join xm_ty y on t.tyid = y.id " +
            " where t.xmid = #{xmid}")
    List<Map<String, Object>> queryLdJtByXmid(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,type,ldid) values (#{bhhs},'HB',#{ldid})")
    void hbSubmit(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,type,sbid) values (#{dbbs},'DB',#{dbid})")
    void dbSubmit(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,type,sbid) values (#{wbhs},'WJJLB',#{wbid})")
    void wbSubmit(Map<String, Object> pd);

    @Select("select nvl(db,0) db,nvl(hb,0) hb,nvl(wb,0) wb,(select count(1) from SGXT_RW_GW where SGXDRWID =#{sgdid}) gwCount from (" +
            " select * from (select type,count(1) nums from sgxt_rw_sb t where sgxdrwid=#{sgdid} group by type) " +
            " pivot(sum(nums) for type in ('DB' db,'HB' hb,'WJJLB' wb)))")
    List<Map<String,Object>> findXmfw(Map<String, Object> pd);

    @Delete("delete from sgxt_rw_sb where type = 'DB' and sgxdrwid is null")
    void dbdelete(Map<String, Object> pd);

    @Delete("delete from sgxt_rw_sb where type = 'WJJLB' and sgxdrwid is null")
    void wbdelete(Map<String, Object> pd);

    @Select("select * from sgxd_wb x where x.xm_id = #{xmid}")
    List<Map<String, Object>> findWjjlbAll(Map<String, Object> pd);

    @Select("select * from sgxd_db x where x.xm_id = #{xmid}")
    List<Map<String, Object>> findDbAll(Map<String, Object> pd);

    @Select("select * from sgxd_hb x where x.xm_id = #{xmid}")
    List<Map<String, Object>> findHbAll(Map<String, Object> pd);

    @Select("select y.wlbm,y.wlmc,y.ggxh,y.wljbfl,y.jbjldw,x.sxsl xysl from xm_wlqd x " +
            " left join xtgl_wlk y on x.wlkid = y.id where x.xmid = #{xmid}")
    List<Map<String, Object>> findWlxxAll(Map<String, Object> pd);

    @Select("select j.* from sgxt_rw_jlfw j left join sgxt_rwb r on j.sgxtrwid = r.id " +
            " where r.xmid = #{xmid} and r.id=#{id}")
    List<Map<String, Object>> queryJlfwByXmid(Map<String, Object> pd);

    @Select("select * from sgxt_rwb s where s.xmid = #{xmid} and s.id = #{sgdid}")
    List<Map<String, Object>> querySgxdByXmid(Map<String, Object> pd);

    @Select("select t.proc_inst_id,t.c_activityid from wf_ru_inst r left join wf_task t " +
            " on r.proc_inst_id = t.proc_inst_id where  " +
            " (case when substr(t.refid ,1,instr(t.refid ,',',1,1)-1) is null then t.refid " +
            "  else substr(t.refid ,1,instr(t.refid ,',',1,1)-1) end ) = #{id}")
    List<Map<String, Object>> queryPdefId(@Param("id") String id,@Param("lcscId") String lcscId);
    //r.pdef_id = #{lcscId}
    // and (case when substr(t.refid ,1,instr(t.refid ,',',1,1)-1) is null then t.refid
    // else substr(t.refid ,1,instr(t.refid ,',',1,1)-1) end ) = #{id}

    @Select("select t.proc_inst_id,t.c_activityid from wf_ru_inst r left join wf_task t " +
            " on r.proc_inst_id = t.proc_inst_id where  " +
            " t.refid = #{id}")
    List<Map<String, Object>> queryPdefIdAndBmmc(@Param("id") String id);

    @Delete("delete from wf_task where c_activityid = #{pdefId} and proc_inst_id=#{procInstId}")
    void deleteTask(@Param("pdefId") String pdefId, @Param("procInstId") String procInstId);

    @Delete("delete from wf_task where c_activityid = #{pdefId} and proc_inst_id=#{procInstId} and refid = #{refid}")
    void deleteTasks(@Param("pdefId") String pdefId, @Param("procInstId") String procInstId,@Param("refid") String refid);

    @Delete("delete from wf_ru_inst where proc_inst_id = #{procInstId}")
    void deleteInst(String procInstId);

    @Delete("delete from wf_ru_inst_content where proc_inst_id = #{procInstId} and contentname = #{id}")
    void deleteInstContent(@Param("procInstId") String procInstId, @Param("id") String id);

    @Select("select a.*,nvl(b.num,0) num from " +
            "(select userid,username from xtgl_user where userid in " +
            " (select userid from xtgl_userrole where roleid = " +
            "  (select w.roleid from wf_activity_role w where w.activityid = #{activityId}))) a " +
            " left join " +
            "(select xmgzry,count(1) num from sgxt_rwb group by xmgzry) b on a.userid = b.xmgzry order by num")
    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    @Update("update sgxt_rwb set SHZT = #{shzt},SHR = #{shr},SHRQ = sysdate where ID = #{id}")
    void updateShzt(Map<String, Object> pd);

    @Update("update sgxt_rwb set SHZT = #{shzt},SHR = #{shr},SHRQ = sysdate,THYY=#{thyy} where ID = #{id}")
    void updateShth(Map<String, Object> pd);

    @Update("update sgxt_rwb set XMGZRY = #{xmgzry} where ID = #{id}")
    void updateZmgzry(Map<String, Object> pd);

    @Update("update sgxt_rwb set ZT = #{zt} where ID = #{id}")
    void updateRwzt(Map<String, Object> pd);

    @Select("call p_get_number(#{v_stop_run,mode=IN,jdbcType=INTEGER},#{p_result,mode=OUT,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    String texuChange(Map<String, Object> map);

    @Update("update wf_ru_inst set pdef_id=#{pdef_id} where proc_inst_id = #{procInstId}")
    void updateWfruInst(@Param("procInstId") String procInstId, @Param("pdef_id") String pdef_id);

    @Select("select userid from xtgl_userrole where " +
            " roleid =(select roleid from wf_activity_role where activityid = #{activityId})")
    String getUserIdByActivityId(@Param("activityId") String activityId);

    @Select("select * from sgxt_rwb s where id = #{sgdid}")
    List<Map<String, Object>> getSgrwdById(String sgdid);

    @Select("<script>" +
            "select * from (select t.*,ROWNUM rn  from (select t.* from GET_XMLB_SG t where t.xmfzr=#{xmfzr} " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%')  " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%')  " +
            "</when>" +
            " order by t.lxsj desc) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXmlbSgEntity> getSgxdsList(Map<String, Object> pd);

    @Select("<script>" +
            "select t.*,ROWNUM rn from GET_XMLB_SG t where t.xmfzr=#{xmfzr} " +
            "<when test=\"xmbh != null and xmbh != '' \">" +
            " and t.xmbh LIKE CONCAT(CONCAT('%', #{xmbh}), '%')  " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%')  " +
            "</when>" +
            "</script>")
    List<GetXmlbSgEntity> getAllSgxdList(Map<String, Object> pd);

    @Select("select * from (select g.*,rownum rn from " +
            " (select * from get_sgxtzb order by fqrq desc) g where g.xmid = #{xmid} ) " +
            " where rn > #{pagebefore} AND rn <= #{pageafter}")
    List<GetsgxtzbEntity> getSggcdList(Map<String, Object> pd);

    @Select("select * from get_sgxtzb where xmid = #{xmid}")
    List<GetsgxtzbEntity> getAllSggcdList(Map<String, Object> pd);

    @Select("select * from WF_TASK where PROC_INST_ID = #{procInstId} and C_ACTIVITYID " +
            " in ('80004','80005','80006','80007','80008','80010','80011','80012','80013','80014')")
    List<WfTaskEntity> findByProcInstId(String procInstId);

    @Select("select id from sgxt_rw_sb where sgxdrwid = #{sgdid}")
    List<String> queryGxsbidBySgdId(Map<String, Object> pd);

    @Select("select s.hh,s.mph,s.ysxzbh,s.hyflbh from xm_sb s where s.ldid = #{ldid}")
    List<Map<String,Object>> querySbByLdid(String ldid);

    @Select("select * from xm_instance x where x.id =#{xmid}")
    List<Map<String, Object>> queryxmxxByid(String xmid);

    @Select("select t.* from xm_instance t where t.id = #{xmid}")
    List<Map<String, Object>> querySqlxByXmid(Map<String, Object> pd);

    @Select("select r.userid from xtgl_userrole r where r.roleid = #{roleId}")
    String getUserIdByroleId(String roleId);

    @Select("select rwblr from wf_task_history where c_activityid = #{actId} and refid is null " +
            " and proc_inst_id = #{procInstId}")
    List<String> queryRwblr(@Param("actId")String actId,@Param("id") String id,
                            @Param("procInstId") String procInstId);

    @Select("select x.*,u.username xmfzrname,u.mobilephone xmfzrsjh,c.codename azlxname from xm_instance x left join xtgl_user u " +
            " on x.xmfzr = u.userid left join (select * from xtgl_code c where c.parentid " +
            " in (select id from xtgl_code where parentid = (select c.id from xtgl_code c where c.type = 'azlx'))) c " +
            " on x.azlx = c.codevalue where x.id = #{xmid}")
    List<XmInstance> getXmxxByid(String xmid);

    @Select("select * from XTGL_USER where userid = #{userid}")
    UserEntity getUserByUserId(String userid);

    @Delete("delete from sgxt_rw_sb s where s.sgxdrwid = #{sgdid} and s.type='WJJLB'")
    void wbEditdelete(Map<String, Object> pd);

    @Delete("delete from sgxt_rw_sb s where s.sgxdrwid = #{sgdid} and s.type='HB' and s.ldid=#{ldid}")
    void hbEditdelete(Map<String, Object> pd);

    @Delete("delete from sgxt_rw_sb s where s.sgxdrwid = #{sgdid} and s.type='DB'")
    void dbEditdelete(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,sgxdrwid,type,sbid,ldid) values (#{bhhs},#{sgdid},'HB',#{sbid},#{ldid})")
    void hbEditSubmit(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,sgxdrwid,type,sbid) values (#{dbbs},#{sgdid},'DB',#{dbid})")
    void dbEditSubmit(Map<String, Object> pd);

    @Insert("insert into sgxt_rw_sb(id,sgxdrwid,type,sbid) values (#{wbhs},#{sgdid},'WJJLB',#{wbid})")
    void wbEditSubmit(Map<String, Object> pd);

    @Select("select * from ht_qdzb where xmid = #{xmid}")
    List<HtQdzbEntity> getHtxx(String xmid);

    @Select("select * from xtgl_user where userid = #{xmjl}")
    List<UserEntity> getUserxx(String xmjl);

    @Select("select t.id,('['||a.mc||']'||'['||t.ldbh||']'||'['||" +
            "(case t.lfsx when 0 then '多层' when 1 then '高层' else '' end)||'户数:'||" +
            " (select count(1) from xm_sb b where b.xm_id=#{xmid} and b.ldid=t.id and b.zt='0')||']') label " +
            " from XM_LD t " +
            " left join xm_ty a on a.id=t.tyid " +
            " where t.xmid=#{xmid} and t.zt='1' ")
    List<CodeModel> initLdhb(@Param("xmid") String xmid);

    @Select("select z.* from (" +
            "select t.id,t.xm_id xmid,t.ldid,t.hh,t.hm,t.dz,t.kjbh,t.ysxzbh,t.hyflbh,t.jffsbh,t.bzlb,t.hhlxbh,t.KHYH,t.KHMC,t.YHZH" +
            "             ,a.mc ysxzname,h.mc hyflname,t.mph,t.ykjbh," +
            "(select count(1) from sgxt_rw_sb where SBID=t.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmId}) and sgxdrwid<>#{sgdid}) checkable," +
            "             (select LDBH from XM_LD where id=t.LDID) ldbh," +
            "             (select c.codename from XTGL_CODE c where t.jffsbh=c.codevalue and c.parentid=(select id from XTGL_CODE where type='jffs')) jffsname," +
            "             d.mc kjname,f.mc ykjname,e.mc khyhname,e.yhbh,e.yhmc khzhname,case when cd.tzdbh is null then '未缴费' else '已缴费'end jfzt,p.sgxdrwid" +
            "      from XM_SB t" +
            "       left join xtgl_ysxz a on t.ysxzbh=a.bh" +
            "       left join xtgl_hyfl h on t.ysxzbh=h.bh" +
            "       left join XTGL_KJ d on t.kjbh=d.bh" +
            "       left join XTGL_KJ f on t.ykjbh=f.bh" +
            "       left join XTGL_FZYH e on t.khyh=e.bh" +
            "       left join (select s.*,r.xmid from sgxt_rw_sb s left join sgxt_rwb r on s.sgxdrwid = r.id where s.type = 'HB') p on t.id = p.sbid and t.ldid = p.ldid" +
            "       left join (select z.*,s.sbid from sfkp_sftzdzb z left join sfkp_sftzdcb c on z.id = c.sftzdzbid left join sfkp_sftzdcbsb s on c.id = s.sftzdcbid where z.tzdzt = 1 and s.sbid is not null) cd on cd.sbid = t.id and cd.xmid = t.xm_id " +
            "       where t.hhlxbh='HB' and t.ZT=0  order by t.hh desc)z" +
            "       where z.ldid = #{ldid} and z.xmid=#{xmId} order by z.hh")
    List<XmSb> initDbpzTable(@Param("xmId") String xmId,@Param("sgdid") String sgdid, @Param("ldid") String ldid);

    @Select("select * from sgxd_ldsb w where w.ldid = #{ldid} and w.xmid=#{xmId} and sgxdrwid=#{sgdid}")
    List<XmSb> initDbpzTables(@Param("xmId") String xmId, @Param("sgdid") String sgdid, @Param("ldid") String ldid);

    @Select("select w.sgxdrwid from sgxd_ldsb w where w.xmid=#{xmid} and w.sgxdrwid is not null " +
            " and w.sgxdrwid not in (select s.id from sgxt_rwb s)")
    List<String> querySgdidByxmid(String xmid);

    @Delete("delete from sgxt_rw_sb where sgxdrwid = #{sgdid}")
    void deleteSgsb(String sgdid);

    @Select("select a.*,b.mc gdkj_vue,c.mc gdcz_vue,e.CODENAME gwzt_vue," +
            "(select count(1) from SGXT_RW_GW where gwid=a.id and sgxdrwid=#{sgdid}) selected," +
            "(select count(1) from SGXT_RW_GW where gwid=a.id and sgxdrwid in (select id from SGXT_RWB where xmid=#{xmid}) and sgxdrwid<>#{sgdid}) checkable from XM_GD a" +
            "        left join xtgl_gdkj b on a.GDKJ=b.BH" +
            "        left join xtgl_gxcz c on a.GDCZ = c.BH" +
            "        left join (select d.* from XTGL_CODE d where d.PARENTID in (select id from XTGL_CODE where type='gwzt')) e on e.CODEVALUE=a.GWZT" +
            "        where a.xmid=#{xmid} and a.ZT=1 order by a.CJSJ desc")
    List<GdModel> getGdList(@Param("xmid") String xmid,@Param("sgdid") String sgdid);



    @Delete("delete from SGXT_RW_GW where sgxdrwid = #{sgdid}")
    void gwdelete(Map<String, Object> pd);

    @Insert("insert into SGXT_RW_GW(id,sgxdrwid,gwid) values (#{id},#{sgdid},#{gwid})")
    void insertGw(Map<String, Object> pd);
}
