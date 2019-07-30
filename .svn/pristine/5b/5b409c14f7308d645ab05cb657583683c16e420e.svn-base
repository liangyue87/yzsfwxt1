package com.ljx.hfgsjt.wtys.dao;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.wtys.GetXmlbEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface YsZjqtMapper {
    @Insert("insert into ys_zjqtfj(id,yszjqtid,fjlx_yj,fjlx_ej,fjlj,fjhz,fjmc,scr,scsj )" +
            " values (#{id},#{yszjqtid},#{fjlx_yj},#{fjlx_ej},#{fjlj},#{fjhz},#{fjmc},#{scr},sysdate)")
    void upoadFile(Map<String, Object> pd);

    @Update("update YS_ZJQTZB set qtjg = #{qtjg} where id = #{refid}")
    void saveYsZjqtFk(Map<String, Object> pd);

    @Select("select * from wf_task where refid = #{refid} and refkey = 'YS_ZJQTZB' and c_activityid = '40011'")
    List<Map<String,Object>> findTaskByRefid(Map<String, Object> pd);

    @Select("select * from wf_ru_inst where pdef_id = '40010' and proc_inst_id = #{proc_inst_id}")
    List<Map<String,Object>> findRuInstByPid(Map<String, Object> pd);

    @Insert("insert into wf_hi_inst(proc_inst_id,pdef_id,startdt,startuser,endtime,procstatus,stopuser,stopdt)" +
            " values (#{PROC_INST_ID},#{PDEF_ID},#{STARTDT},#{STARTUSER},sysdate,#{PROCSTATUS},#{STOPUSER},sysdate)")
    void insertWfHiInst(Map<String,Object> map);

    @Insert("insert into wf_hi_inst_content select * from wf_ru_inst_content where proc_inst_id = #{proc_inst_id}")
    void insertWfHiInstContent(Map<String, Object> pd);

    @Delete("delete from wf_ru_inst where pdef_id = 40010 and proc_inst_id = #{proc_inst_id}")
    void deleteWfRuInst(Map<String, Object> pd);

    @Delete("delete from wf_ru_inst_content where proc_inst_id = #{proc_inst_id}")
    void deleteWfRuInstContent(Map<String, Object> pd);

    @Delete("delete from wf_task where c_activityid = '40011' and proc_inst_id = #{proc_inst_id}")
    void deleteTask(Map<String, Object> pd);

    @Select("select * from YS_ZJQTZB where id = #{id}")
    List<Map<String, Object>> queryZjqtzbById(Map<String, Object> pd);

    @Select("select y.username sjry,y.userid sjryid,m.username ysry,m.userid ysryid,y.MOBILEPHONE sjsjh,m.mobilephone yssjh " +
            " from xm_instance x " +
            " left join xtgl_user y on x.sjfzr = y.userid " +
            " left join xtgl_user m on x.ysfzr = m.userid " +
            " where x.id = #{id}")
    List<Map<String,Object>> queryBmById(Map<String, Object> pd);

    @Select("select t.*,x.username from ys_zjqtcb t left join xtgl_user x on t.bzpr = x.userid where t.ZJQTID = #{id}")
    List<Map<String, Object>> queryZjqtcbById(Map<String, Object> pd);

    @Select("<script>" +
            " select t.* from (select t.*," +
            " case when o.contentid is null and w.contentid is null then '0' when o.contentid is not null " +
            "   and w.contentid is null then '1' else '2' end sfzjqt  " +
            " from get_xmlb_zjqt t left join (select b.* from wf_hi_inst a left join wf_hi_inst_content b " +
            "  on a.proc_inst_id = b.proc_inst_id where a.pdef_id = '40011' order by a.startdt desc) w " +
            "  on t.ID = w.contentvalue " +
            " left join (select b.* from wf_ru_inst a left join wf_ru_inst_content b  " +
            " on a.proc_inst_id = b.proc_inst_id where a.pdef_id = '40011' order by a.startdt desc) o " +
            " on t.ID = o.contentvalue) t where t.xmfzr=#{xmfzr} " +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr LIKE CONCAT(CONCAT('%', #{lxr}), '%') " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%') " +
            "</when>" +
            "<when test=\"xmdz != null and xmdz != '' \">" +
            " and t.xmdz LIKE CONCAT(CONCAT('%', #{xmdz}), '%') " +
            "</when>" +
            "<when test=\"kssj != null and kssj != '' \">" +
            " and to_char(t.slsj,'yyyy-mm-dd hh24:mi') &gt;= #{kssj} " +
            "</when>" +
            "<when test=\"jssj != null and jssj != '' \">" +
            " and to_char(t.slsj,'yyyy-mm-dd hh24:mi') &lt;= #{jssj} " +
            "</when>" +
            "</script>")
    List<GetXmlbEntity> getAllXqslList(Map<String, Object> pd);

    @Select("<script>" +
            "select * from (select t.*,ROWNUM rn from (select t.* from (select t.*," +
            " case when o.contentid is null and w.contentid is null then '0' when o.contentid is not null " +
            "   and w.contentid is null then '1' else '2' end sfzjqt  " +
            " from get_xmlb_zjqt t left join (select b.* from wf_hi_inst a left join wf_hi_inst_content b " +
            "  on a.proc_inst_id = b.proc_inst_id where a.pdef_id = '40011' order by a.startdt desc) w " +
            "  on t.ID = w.contentvalue " +
            " left join (select b.* from wf_ru_inst a left join wf_ru_inst_content b  " +
            " on a.proc_inst_id = b.proc_inst_id where a.pdef_id = '40011' order by a.startdt desc) o " +
            " on t.ID = o.contentvalue) t where t.xmfzr=#{xmfzr} " +
            "<when test=\"lxr != null and lxr != '' \">" +
            " and t.lxr LIKE CONCAT(CONCAT('%', #{lxr}), '%') " +
            "</when>" +
            "<when test=\"xmmc != null and xmmc != '' \">" +
            " and t.xmmc LIKE CONCAT(CONCAT('%', #{xmmc}), '%') " +
            "</when>" +
            "<when test=\"xmdz != null and xmdz != '' \">" +
            " and t.xmdz LIKE CONCAT(CONCAT('%', #{xmdz}), '%') " +
            "</when>" +
            "<when test=\"kssj != null and kssj != '' \">" +
            " and to_char(t.slsj,'yyyy-mm-dd hh24:mi') &gt;= #{kssj} " +
            "</when>" +
            "<when test=\"jssj != null and jssj != '' \">" +
            " and to_char(t.slsj,'yyyy-mm-dd hh24:mi') &lt;= #{jssj} " +
            "</when>" +
            "<when test=\"sfbz == 0 \">" +
            " and t.sfzjqt = '0' " +
            "</when>" +
            "<when test=\"sfbz == 1 \">" +
            " and t.sfzjqt = '1' " +
            "</when>" +
            " order by t.lxsj desc ) t) " +
            " WHERE rn &gt; #{pagebefor} AND rn &lt;= #{pageafter}" +
            "</script>")
    List<GetXmlbEntity> getAllXqsl(Map<String, Object> pd);

    @Select("select y.*,u.username,to_char(y.scsj,'yyyy-MM-dd') cjsj from ys_zjqtfj y " +
            " left join xtgl_user u on y.scr = u.userid where y.yszjqtid = #{id}")
    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    @Delete("delete from ys_zjqtfj where id = #{fjId}")
    void deleteFjByid(Map<String, Object> pd);

    @Select("select * from xm_instance where id = #{xmid}")
    List<XmInstance> getXmxxByid(String xmid);

    @Select("select b.contentname from wf_hi_inst a left join wf_hi_inst_content b on a.proc_inst_id = b.proc_inst_id " +
            " where a.pdef_id = '40011' and b.contentvalue=#{xmid}")
    List<String> queryPara(String xmid);

    @Select("select qtjg from YS_ZJQTZB where id = #{zbid}")
    List<String> queryFkByzbid(String zbid);

    @Select("select b.contentname from wf_ru_inst a left join wf_ru_inst_content b on a.proc_inst_id = b.proc_inst_id " +
            " where a.pdef_id = '40011' and b.contentvalue=#{xmid}")
    List<String> queryParas(String xmid);

    @Select("select bz,qtsj from YS_ZJQTZB where id = #{zbid}")
    List<Map<String,Object>> queryFkByzbids(String zbid);
}
