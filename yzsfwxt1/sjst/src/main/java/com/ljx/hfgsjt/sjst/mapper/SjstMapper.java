package com.ljx.hfgsjt.sjst.mapper;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface SjstMapper {
    int getProjectCount(ProjectModel model);
    List<ProjectModel> getProjectList(ProjectModel model);

    List<CodeModel> getTable(@Param("xmid") String xmid,@Param("yjfjlx")String yjfjlx);

    List<SjstYhzl> getQtzl(@Param("xmid")String xmid,@Param("fjlx")String fjlx);

    void insertImportFh(SjstYhzl model);

    void deleteFj(@Param("id")String id,@Param("userid")String userid);

    List<WfRuInstEntity> getInstList(@Param("xmid")String xmid, @Param("activityId")String activityId,@Param("direction")int direction,@Param("type")String type);

    String getPdefId(String activityId);

    void updateInst(@Param("xmid")String xmid, @Param("activityId")String activitiId,@Param("direction")int direction,@Param("rwbh")String rwbh);

    WfTaskEntity getNowTask(@Param("xmid")String xmid, @Param("activityId")String activitiId);

    WfDfActivityEntity getActivityEntity(String activitiId);

    void deleteTask(String taskid);

    List<WfTaskEntity> getNextTaskEntity(@Param("xmid")String xmid, @Param("activityId")String activitiId,@Param("direction")int direction);

    List<WfActivityRoleEntity> getRoleList(@Param("activityId")String activityId, @Param("userid")String userid);

    int getSjrwfpXmlbCount(ProjectModel model);

    List<ProjectModel> getSjrwfpXmlbList(ProjectModel model);

    void updateTask(WfTaskEntity nowTask);

    List<CodeModel> getZlfl(String xmid);

    List<SjstYhzl> getYhzl(@Param("xmid")String xmid, @Param("id")String id);

    void saveSjstRw(SjstRwModel sjstRwModel);

    void saveXmSjstRela(XmSjstRela xmSjstRela);

    List<Map<String,Object>> getZpry(@Param("roleid")String roleid,@Param("xmid")String xmid);


    String getMrzpryid(Map<String,Object>  map);

    void updateSjstRw(SjstRwModel sjstRwModel);

    int getSjrySjrwXmlbCount(ProjectModel model);

    List<ProjectModel> getSjrySjrwXmlbList(ProjectModel model);

    List<CodeModel> getZlzl(@Param("xmid")String xmid,@Param("type")String type,@Param("yjfjlx")String yjfjlx);

    void addTY(TyModel model);

    void updateTY(TyModel model);

    String getKey(Map<String, Object> map);

    List<TyModel> initTYTable(String xmid);

    List<DxsModel> initDXSTable(String xmid);

    void addDXS(DxsModel model);

    void updateDXS(DxsModel model);

    List<BfModel> initBFTable(String xmid);

    void addBF(BfModel model);

    void updateBF(BfModel model);

    List<CodeModel> initBFOption(String xmid);

    void updateSX(XmSx model);

    List<XmSx> findAllByXmid(String xmid);

    List<CodeModel> initLYOption(String xmid);

    List<LdModel> initLDTable(String xmid);

    List<LdModel> checkLdbh(LdModel model);

    void addLD(LdModel model);

    void updateLD(LdModel model);

    void deleteFQ(@Param("ldid") String ldid, @Param("xmid") String xmid);

    void addFQ(Fqmodel model);

    List<Fqmodel> getFQData(@Param("xmid")String xmid,@Param("ldid")String id);

    CodeModel xmsqlx(String xmid);

    List<XjxqldModel> xjxqld(String xmid);

    int getWlDataCount(XtglwlkModel model);

    List<XtglwlkModel> getWlData(XtglwlkModel model);

    int pickHandleCount(XtglwlkModel model);

    List<XtglwlkModel> pickHandle(XtglwlkModel model);

    void wlHandleAdd(XmwlqdModel model);

    void wlqdHandleEdit(XmwlqdModel model);

    void wlqdHandleDelete(String id);

    List<XmwlqdModel> getWlByWlkid(@Param("wlkid") String wlkid, @Param("xmid")String xmid);

    List<FjzlglModel> initStcgTable(@Param("sjstid")String sjstid,@Param("xmid")String xmid,@Param("type")String type,@Param("flag")String flag);

    List<SjstcgModel> stManage(SjstcgModel query);

    void stDelete(@Param("id")String id, @Param("flag")int flag);

    void stcgsc(SjstcgModel model);

    List<CodeModel> getKjData();

    List<CodeModel> getCodeData(String type);

    List<CodeModel> getKhzhData();

    List<CodeModel> getKhyhData(String type);

    void addDb(XmSb model);

    void updateDb(XmSb model);

    List<XmSb> initDbpzTable(@Param("xmId") String xmId,@Param("startRow") int startRow,@Param("endRow") int endRow,@Param("hhlxbh")String hhlxbh,@Param("ldid")String ldid);

    int initDbpzTableCount(@Param("xmId")String xmId, @Param("hhlxbh")String hhlxbh,@Param("ldid")String ldid);

    void dbpzDelete(@Param("id")String id, @Param("zt")int zt);

    List<CodeModel> initLdhb(String xmid);

    void deleteLdhb(@Param("xmId")String xmId,@Param("ldid") String ldid);

    List<XmSb> dbpzAdd(@Param("xmid")String xmid, @Param("ldid")String ldid);

    String getMph(@Param("xmid")String xmid, @Param("ldid")String ldid);

    void mphHandel(@Param("id")String id, @Param("mph")String mph);

    List<WfRuInstContentEntity> queryWfRuInstContentEntity(String id);

    void deleteInst(String procInstId);

    void deleteInstContent(String procInstId);

    void updateSjstRwBz(SjstRwModel sjstRwModel);

    List<CodeModel> getzlTypeOptions();

    List<ProjectModel> getProjectLists(String xmid);

    List<Map<String,Object>> fjzlPreview(@Param("xmid")String xmid, @Param("yjfjlx")String yjfjlx, @Param("ejfjlx")String ejfjlx);

    int submitShow(String xmid);

    List<SjstYhzl> getXmjlQtzl(@Param("xmid")String xmid, @Param("ejfjlx")String ejfjlx);

    List<WfTaskEntity> initHhld(@Param("xmid")String xmid, @Param("activityid")String activityid);

    @Insert("insert into XM_SB(id,XM_ID,JFFSBH,YSXZBH,KJBH,HH,KHYH,KHMC,YHZH,HYFLBH,ZT,HHLXBH,LDID,DZ,bzlb) " +
            " values (#{id},#{xmId},#{jffsbh},#{ysxzbh},#{kjbh},#{hh},#{khyh},#{khmc},#{yhzh},#{hyflbh},#{zt},#{hhlxbh},#{ldid},#{dz},#{bzlb})")
    void addChgzHb(XmSb model);

    @Select("select t.id,t.xm_id xmid,t.hh,t.hm,t.dz,t.kjbh,t.ysxzbh,t.hyflbh,t.jffsbh,t.bzlb,t.hhlxbh,t.KHYH,t.KHMC,t.YHZH " +
            " ,a.codename ysxzname,t.mph, " +
            " (select LDBH from XM_LD where id=t.LDID) ldbh, " +
            "(select b.codename from XTGL_CODE b where t.hyflbh=b.codevalue and b.parentid=t.ysxzbh) hyflname,"+
            " (select c.codename from XTGL_CODE c where t.jffsbh=c.codevalue and c.parentid=(select id from XTGL_CODE where type='jffs')) jffsname, " +
            " d.mc kjname,e.mc khyhname,e.yhbh,e.yhmc khzhname from XM_SB t " +
            " left join (select * from XTGL_CODE where parentid = (select id from XTGL_CODE where type = 'ysxz')) a on t.ysxzbh=a.id " +
            " left join XTGL_KJ d on t.kjbh=d.bh " +
            " left join XTGL_FZYH e on t.khyh=e.bh " +
            " where t.hhlxbh='HB' and t.xm_id=#{xmid} and t.ZT=0 and t.LDID in ${ldids}")
    List<XmSb> queryAllLdsb(Map<String, Object> pd);

    @Update("update xm_sb set hm = #{hm},mph=#{mph},sfzh=#{sfzh},zjlxbh='01',lxdh=#{lxfs} where hh=#{hh}")
    void updateMphAndSfzhByhh(Map<String, Object> pd);

    List<Map<String,Object>> queryGdczList();

    List<Map<String,Object>> queryKjList();

   List<GdModel> getGdList(String xmid);

    List<GdModel> getGdListByid(String id);

    List<GlgModel> getGlgList(String id);

    void GWHandleDelete(String id);

    void GLGHandleDelete(String id);

    int getTcxmCount(ProjectModel model);

    List<Map<String,Object>> getTcxmtList(ProjectModel model);

    void updateXmjlsjh(@Param("xmfzr")String xmfzr, @Param("xmfzr_sjh")String xmfzr_sjh);

    void updateXmjbxx(@Param("xmid")String xmid, @Param("xmmc")String xmmc, @Param("xmdz")String xmdz, @Param("lxr")String lxr, @Param("lxrsjh")String lxrsjh);

    List<Map<String,Object>> buttonshow(@Param("activityId")String activityId, @Param("direction")int direction);

    void updatezdTask(@Param("xmid")String xmid, @Param("activityId")String activityId, @Param("radio")String radio);

    List<SjstcgModel> ksjsfaManage(SjstcgModel query);

    List<CodeModel> getHyflCodeData(String parentid);

    void stopProject(@Param("xmid")String xmid,@Param("zzyy") String zzyy);

    List<Map<String,Object>> getsqyqxx(@Param("xmid")String xmid, @Param("activityId")String activityId);

    List<Map<String,Object>> getTask(@Param("xmid")String xmid,@Param("activityId") String activityId);

    void sqyq(@Param("id")String id, @Param("taskId")String taskId, @Param("jhyqjz_vue")String jhyqjz_vue, @Param("csyy")String csyy,@Param("yqwcsj")String yqwcsj,@Param("sprid")String sprid,@Param("sqrid")String sqrid);

    List<Map<String,Object>> getActivity(String activityId);

    int getspXmlbCount(@Param("xmbh")String xmbh, @Param("spzt")String spzt, @Param("kssj")String kssj, @Param("jssj")String jssj,@Param("spr")String spr);

    List<Map<String,Object>> getspXmlb(@Param("xmbh")String xmbh, @Param("spzt")String spzt, @Param("kssj")String kssj, @Param("jssj")String jssj, @Param("startRow")int startRow, @Param("endRow")int endRow,@Param("spr")String spr);

    void passdelay(@Param("id")String id, @Param("spsfty")String spsfty,@Param("spyj") String spyj);

    List<Map<String,Object>> getDelay(String taskid);

    List<Map<String,Object>> getxmList(String xmid);

    void updateTasksj(@Param("jhyqsj")String jhyqsj, @Param("id")String id);

    List<Map<String,Object>> getRwblr(@Param("activityId")String activitiId, @Param("direction")int direction);

    List<SjstSxpzb> initSjsx();

    List<SjstRwModel> getSjstrw(String xmid);

    void updaterwb(@Param("xmid")String xmid,@Param("radio") String radio);

    List<Map<String,Object>> getRwtxfsdx(@Param("xmid")String xmid, @Param("activitiId")String activitiId, @Param("direction")int direction);

    List<Map<String,Object>> getdxList(@Param("xmid")String xmid, @Param("activitiId")String activitiId, @Param("direction")int direction,@Param("tablename")String tablename, @Param("id")String id);

    List<Map<String,Object>> getTaskRwtxfsdx(@Param("xmid")String xmid, @Param("activitiId")String activitiId, @Param("direction")int direction);

    @Select("select t.id,z.xmid,t.fjlx_yj fjlxYj,t.fjlx_ej fjlxEj,t.fjlj,t.fjhz,t.fjmc,t.scr," +
            "t.scsj,t.sc_flag scFlag,t.shcr,t.shcsj,to_char(t.scsj,'yyyy-MM-dd') scsj_vue " +
            " from ht_qdzbfj t left join ht_qdzb z on t.htqdid = z.id where z.xmid = #{xmid}")
    List<SjstYhzl> getHtqdzl(@Param("xmid") String xmid, @Param("ejfjlx") String ejfjlx);

    GetXqslEntity getXmjbxxById(String xmid);

    void insertImportFh_xqsl(Map<String, Object> pd);

    int gettoDetial_xqslCount(Map<String, Object> pd);

    List<Map<String,Object>> gettoDetial_xqslList(Map<String, Object> pd);

    void deleteFj_xqsl(@Param("id")String id, @Param("userid")String userid);

    void handleAdd(ProjectModel model);

    void getSblhh(Map<String, Object> map);

    List<Map<String,Object>>  getSbxx(String id);

    void updateSblhh(@Param("id")String id, @Param("db")String db);

    List<LdModel> getLdList(@Param("ldid")String ldid);

    void insertImportFh_delay(Map<String, Object> pd);

    List<Map<String,Object>> gettoDetial_delayList(@Param("delayid")String delayid);

    void deleteDelayFj(@Param("id")String id, @Param("userid")String userid);

    List<Map<String,Object>> getsjstrwList(@Param("xmid")String xmid);

    List<Map<String,Object>> getdelayList(@Param("id")String id);

    void editChgzHb(XmSb model);

    List<CodeModel> getYsxzData();

    List<CodeModel> getHyflData();
}
