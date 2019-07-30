package com.ljx.hfgsjt.main.dao;


import com.ljx.hfgsjt.entity.dto.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface WorkMapper {

    @Select("<script>" +
            "select * from (select s.*,rownum rn from (" +
            "select * from GET_WDRW_DBLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "order by t.fssj desc) s where rownum &lt;= #{endRow})" +
            "where rn &gt;= #{startRow}"+
            "</script>"
            )
    List<Map<String,Object>> getMyBacklog(BaseEntity baseEntity);//获取我的待办任务
    @Select("<script>" +
            "select COUNT(1) from GET_WDRW_DBLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "</script>"
            )
    int getMyBacklogTotal(BaseEntity baseEntity);//获取我的待办任务数量

    @Select("<script>" +
            "select * from (select s.*,rownum rn from (" +
            "select * from GET_WDRW_YBLLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "order by t.fssj desc) s where rownum &lt;= #{endRow})" +
            "where rn &gt;= #{startRow}" +
            "</script>"
            )
    List<Map<String,Object>> getMyDone(BaseEntity baseEntity);//获取我办理的任务
    @Select("<script>" +
            "select COUNT(1) from GET_WDRW_YBLLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "</script>")
    int getMyDoneTotal(BaseEntity baseEntity);//获取我办理的任务

    @Select("<script>" +
            "select * from (select s.*,rownum rn from (" +
            "select * from GET_WDRW_FQLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.startuser = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "order by t.fssj desc) s where rownum &lt;= #{endRow})" +
            "where rn &gt;= #{startRow}"+
            "</script>"
            )
    List<Map<String,Object>> getMyOriginate(BaseEntity baseEntity);//获取我发起的任务
    @Select("<script>" +
            "select COUNT(1) from GET_WDRW_FQLB t where 1=1 " +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.startuser = #{rwblr}"+
            "</when>"+
            "<when test=\"kssj != null and kssj != ''\">"+
            "and t.qssj &gt;= to_date(#{kssj},'yyyy-mm-dd')"+
            "</when>"+
            "<when test=\"jssj != null and jssj != ''\">"+
            "and t.qssj &lt; to_date(#{jssj},'yyyy-mm-dd') +1"+
            "</when>"+
            "<when test=\"dwmc != null and dwmc != ''\">"+
            "and t.xmmc like concat(concat('%',#{dwmc}),'%')"+
            "</when>"+
            "<when test=\"lxr != null and lxr != ''\">"+
            "and t.lxr like concat(concat('%',#{lxr}),'%')"+
            "</when>"+
            "<when test=\"rwbh != null and rwbh != ''\">"+
            "and t.rwbh like concat(concat('%',#{rwbh}),'%')"+
            "</when>"+
            "<when test=\"type != null and type != ''\">"+
            "and substr(t.c_activityid,0,1) = #{type}"+
            "</when>"+
            "</script>"
            )
    int getMyOriginateTotal(BaseEntity baseEntity);//获取我发起的任务

    @Select("select * from XTGL_HJBM order by sort")
    List<Map<String,String>> getTypeCheckbox();


    @Select("select * from GET_XMXX_XMJL t where xmjl = #{userid}")
    List<Map<String,Object>> getXmData(@Param("userid") String userid);


    @Select("select * from GET_HHLDTL_XMJL t where xmjl = #{userid}")
    Map<String,Object> getHhldData(@Param("userid") String userid);

    @Select("select * from GET_ALLTASK_WORK t where t.xmid = #{xmid} and substr(t.c_activityid,0,1) = #{c_activityid}")
    List<Map<String,Object>> getAllInstInfo(@Param("xmid") String xmid,@Param("c_activityid") String c_activityid);

    @Select("select sum(sfhuangd)-sum(sfhongd) as yellow,sum(sfhongd) as red, substr(t.c_activityid,0,1) as act " +
            "       from get_alltask_work t where xmid = #{xmid} group by substr(t.c_activityid,0,1)")
    List<Map<String,Object>> getAllInstInfoNum(@Param("xmid") String xmid);

    @Select("select z.*" +
            "        from (select ROWNUM rn,tt.* from(" +
            "        select a.taskid,c.xmbh,c.xmmc,d.activityname,a.rwblr,e.username rwblrname,d.finish_limited,to_char(a.JSSJ,'yyyy-MM-dd HH24:mi') jssj," +
            "        to_char(a.JSSX,'yyyy-MM-dd HH24:mi') JSSX,to_char(a.JSSX_YL,'yyyy-MM-dd HH24:mi') JSSX_YL" +
            "        from wf_task_history a" +
            "        left join (select * from wf_ru_inst_content where upper(table_name) = 'XM_INSTANCE' union all select * from wf_hi_inst_content where upper(table_name) = 'XM_INSTANCE') b on a.proc_inst_id = b.proc_inst_id" +
            "        left join (select * from xm_instance where PXMID is not null ) c on b.CONTENTVALUE = c.id" +
            "        left join wf_df_activity d on a.c_activityid = d.activityid" +
            "        join xtgl_user e on a.rwblr = e.userid" +
            "        where c.xmfzr=#{userid}  and a.rwblr is not null and a.jssj is not null and a.jssx is not null and a.jssx_yl is not null order by a.JSSJ desc" +
            "        )tt)z" +
            "        where z.rn between 1 and 3")
    List<Map<String,Object>> getMessageList(@Param("userid") String userid);
}
