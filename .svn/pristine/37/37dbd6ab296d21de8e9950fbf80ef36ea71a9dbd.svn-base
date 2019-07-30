package com.ljx.hfgsjt.xkybz.dao;

import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstContentEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfRuInstEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskEntity;
import com.ljx.hfgsjt.entity.dto.Activity.WfTaskHistoryEntity;
import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.CodeEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.SjstYhzl;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.xkybz.*;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslChgzsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslGshbsqbEntity;
import com.ljx.hfgsjt.entity.xqsl.YwslXjxqsqbEntity;
import com.ljx.hfgsjt.entity.yj.GetXmlbSbyjEntity;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

public interface XkylxMapper {
    void deleteFkFj(@Param("tablename")String tablename,@Param("id") String id);
    List<XckcFkFj> getPjFjlb(@Param("tablename") String tablename, @Param("kcrwzbid") String kcrwzbid);
    GetXckcxxView getXmxxFromFq(@Param("id")String id);
    GetXckcxxView getXmxxFromSqd(@Param("id")String id);
    GetXckcxxView getXckcxxByView(@Param("id")String id);
    String getQszprByQybm(@Param("qybm")String qybm);
    String getQsryRoleIdByQybm(@Param("qybm")String qybm);
    List<KcrwfqView> getKcrwfq(KcrwfqView kcrwfqView);
    Integer getKcrwfqTotal(KcrwfqView kcrwfqView);
    List<GetXckcxxView> getXckcZpTask(GetXckcxxView getXckcxxView);
    Integer getXckcZpTotal(GetXckcxxView getXckcxxView);
    XckcRwcbEntity getXckcRwcb(@Param("xmid") String xmid, @Param("type") String type, @Param("kczt") String kczt);
    List<XckcRwcbEntity> findAllXckcRwcbBySffk(@Param("xmid") String xmid, @Param("sffk") String sffk);
    List<Map<String ,Object>> getZpry(@Param("roleid") String roleid);
    void saveXmXckcRela(Map<String,Object> xmXckcRela);//保存项目现场勘查关联表
    XckcRwzbEntity getXckcRwzbEntity(@Param("id") String id);//通过id获取勘查的任务主表信息
    void saveFj(Map map);
    void savePjFj(Map map);
    void saveKcjgqrFj(Map map);
    List<CodeEntity> getGxkj();//管线口径
    List<CodeEntity> getGxcz();//管线材质
    List<CodeEntity> getZsffgw();//正上方覆盖物
    List<CodeEntity> getZlwz();//主路位置
    List<GetXckcxxView> getKcrwFk(@Param("kcbm") String kcbm);
    List<GetXckcxxView> getKcrwPj();
    List<GetXckcxxView> getKcrwYhqr();
    List<XckcRwzbEntity> getKczbByXmid(@Param("xmid") String xmid,@Param("kczt") String kczt);
    XckcRwzbEntity getXckcxx(@Param("xmid") String xmid);
    XckcRwzbEntity getXckcRwzbIsDoneBySqdId(@Param("id") String id);//通过申请单ID查找已经完成勘查的勘查主表信息
    List<XckcRwcbEntity> getKcbm(@Param("kcrwzbid") String kcrwzbid);
    List<XckcFkQsEntity> showQsFk(@Param("id") String id);
    List<XckcFkFj> getFkFjlb(@Param("tablename") String tablename, @Param("kcrwcbid") String kcrwcbid);
    List<XckcFkFj> getSgdwFkFjlb(@Param("kcrwcbid") String kcrwcbid);
    XckcFkSgdwEntity getSgdwKcfk(@Param("kcrwcbid") String kcrwcbid);
    WfRuInstEntity getWfRuInstByXmidAndPdefid(@Param("activityid") String activityid,@Param("xmid") String xmid);//
    List<WfRuInstContentEntity> getWfRuInstContentByInstId(String id);
    WfTaskEntity getTaskByInstId(@Param("instid")String instid, @Param("nodekey")String nodekey, @Param("attrvalue")String attrvalue);
    List<WfTaskEntity> getTaskAllByInstId(@Param("instid")String instid);
    List<GetSqbxxView> getLxlb(GetSqbxxView e);
    int getLxlbTotal(GetSqbxxView e);
    List<GetSqbxxView> getCheckZxm(XmInstance e);//获取主项目列表
    int getCheckZxmTotal(XmInstance e);//数目

    @Select("select * from WF_TASK_HISTORY WHERE PROC_INST_ID = #{procInstId}")
    List<WfTaskHistoryEntity> getAllTaskHistory(String procInstId);

    @Select("select t.*,to_char(t.slrq,'yyyy-mm-dd') slrq_str," +
            "to_char(t.jfsj,'yyyy-mm-dd') jfsj_str from get_xqsl t where id = #{id}")
    GetXqslEntity getSlxx(@Param("id")String id);
    YwslXjxqsqbEntity getSsbxxJmk(@Param("id")String id);
    YwslGshbsqbEntity getSsbxxGsk(@Param("id")String id);
    YwslChgzsqbEntity getSsbxxHgk(@Param("id")String id);
    int updateSsbSflx(@Param("tablename") String tablename,@Param("id")String id);

    List<CodeEntity> getXmCodeCheckbox(@Param("codename") String codename,@Param("type") String type);
    List<XmInstanceEntity> getZxm();

    int setXmidToLdxx(@Param("sqid") String sqid,@Param("xmid") String xmid);

    int findXmNumForParentId(@Param("id")String id);

    void saveSlfj(Map map);

    List<CodeModel> getZlzl(@Param("tablename") String tablename, @Param("fieldname") String fieldname, @Param("sqdid") String sqdid, @Param("fjlx") String fjlx);//获取受理资料类型数量列表

    List<SjstYhzl> getYhzl(@Param("tablename") String tablename, @Param("fieldname") String fieldname, @Param("sqdid") String sqdid, @Param("fjlx") String fjlx);//获取受理资料某一类的附件

    List<Map<String,Object>> findSqFjLb(@Param("tablename") String tablename,@Param("name") String name,@Param("id") String id);

    @Select("select * from XTGL_GSS where bh = (select qsbh from XTGL_QSDYB where qybh = #{qybh})")
    Map<String ,String> getGssByXzqybh(@Param("qybh") String qybh);

    @Select("call p_get_number(#{v_stop_run,mode=IN,jdbcType=INTEGER},#{p_result,mode=OUT,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    Map<String,Object> getRwbh(Map map);//调用存储过程获取任务编号

    @Select("call p_get_xmbh_ym(#{v_stop_run,mode=IN,jdbcType=INTEGER},#{p_result,mode=OUT,jdbcType=VARCHAR})")
    @Options(statementType = StatementType.CALLABLE)
    Map<String,Object> getXmbh(Map map);//调用存储过程获取项目编号

    @Select("select userid from(" +
            "select userid,rownum from xtgl_user where userid in" +
            "       (select userid from xtgl_userrole where roleid =" +
            "            (select roleid from wf_activity_role where activityid =#{activityId})) and rownum = '1' )")
    String getUserIdByActivityId(@Param("activityId") String activityId);

    @Select("select * from GET_XMLB_SBYJ where id = #{xmid}")
    List<GetXmlbSbyjEntity> getYjXmxx(String xmid);

    @Select("select t.*,f_get_username(t.xmfzr) xmfzr_str,f_get_mobilephone(t.xmfzr) xmfzr_sjh," +
            "f_get_xzqyname(t.xmdz_xzqybh) ssqy_str from xm_instance t where id = #{xmid}")
    XmInstanceEntity getXmxxById(@Param("xmid") String xmid);

    List<Map<String,Object>> getFkFjData_sjy(@Param("cbid")String cbid);

    List<Map<String,Object>> getFkFjData_sgdw(@Param("cbid")String cbid);

    List<SjstYhzl> toDetial_sjy(String id, String ejfjlx, String yjfjlx);

    @Select("select * from XTGL_USER where userid = #{userid}")
    UserEntity getUserByUserId(@Param("userid")String userid);

    List<XckcFkQsEntity> getQsfk(@Param("kcrwcbid")String kcrwcbid,@Param("dcnr")String dcnr);//c_activityid



    @Select("<script>" +
            "select * from (select s.*,rownum rn from (" +
            "select t.*,u.username,u.mobilephone from APP_RWDBLB t left join XTGL_USER u on t.startuser=u.userid where t.c_activityid in ('20031','20032','20033','20034','20006')" +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "order by t.jssx_yl desc,t.jssx desc,t.startdt desc) s where rownum &lt;= #{endRow})" +
            "where rn &gt;= #{startRow}"+
            "</script>"
    )
    List<Map<String,Object>> getMyBacklog(BaseEntity baseEntity);//获取我的待办任务
    @Select("<script>" +
            "select COUNT(1) from APP_RWDBLB t where t.c_activityid in ('20031','20032','20033','20034','20006')" +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "</script>"
    )
    int getMyBacklogTotal(BaseEntity baseEntity);//获取我的待办任务数量

    @Select("<script>" +
            "select * from (select s.*,rownum rn from (" +
            "select t.*,u.username,u.mobilephone from GET_WDRW_YBLLB t left join XTGL_USER u on t.startuser=u.userid where t.c_activityid in ('20031','20032','20033','20034','20006')" +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "order by t.jssx_yl desc,t.jssx desc,t.startdt desc) s where rownum &lt;= #{endRow})" +
            "where rn &gt;= #{startRow}"+
            "</script>"
    )
    List<Map<String,Object>> getMyDone(BaseEntity baseEntity);//获取我办理的任务
    @Select("<script>" +
            "select COUNT(1) from GET_WDRW_YBLLB t where t.c_activityid in ('20031','20032','20033','20034','20006')" +
            "<when test=\"rwblr != null and rwblr != ''\">"+
            "and t.rwblr = #{rwblr}"+
            "</when>"+
            "</script>"
    )
    int getMyDoneTotal(BaseEntity baseEntity);//获取我办理的任务数量

     @Select("select t.* from XCKC_PJ_FJ t where t.kcrwzbid=#{kcfkid}")
    List<XckcFkFj> xmjlCk(@Param("kcfkid")String kcfkid);

     @Select("select xmbh_jc from xtgl_code where parentid in (" +
             "select id from xtgl_code where parentid in (" +
             "select id from xtgl_code where type = 'azlx')) and rownum = 1 and codevalue = #{azlx}")
    String getAzlxbm(String azlx);//项目编码 安装类型编码
}
