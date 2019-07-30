package com.ljx.hfgsjt.sjst.service;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;

import java.util.List;
import java.util.Map;

public interface SjstService {
    int getProjectCount(ProjectModel model);

    List<ProjectModel> getProjectList(ProjectModel model);

    List<CodeModel> getTable(String xmid,String yjfjlx);

    List<SjstYhzl> getQtzl(String xmid,String fjlx);

    void insertImportFh(SjstYhzl model);

    void deleteFj(String id,String userid);

    List<WfRuInstEntity> getInstList(String xmid, String activityId,int direction,String type);

    String getPdefId(String activityId);

    void updateInst(String xmid, String activitiId ,int direction,String rwbh);

    WfTaskEntity getNowTask(String xmid, String activitiId);

    WfDfActivityEntity getActivityEntity(String activitiId);

    void deleteTask(String taskid);

    List<WfTaskEntity> getNextTaskEntity(String xmid, String activitiId,int direction);

    List<WfActivityRoleEntity> getRoleList(String activityId, String userid);

    int getSjrwfpXmlbCount(ProjectModel model);

    List<ProjectModel> getSjrwfpXmlbList(ProjectModel model);

    void updateTask(WfTaskEntity nowTask);

    List<CodeModel> getZlfl(String xmid);

    List<SjstYhzl> getYhzl(String xmid, String id);

    void saveSjstRw(SjstRwModel sjstRwModel);

    void saveXmSjstRela(XmSjstRela xmSjstRela);

    List<Map<String,Object>> getZpry(String roleid,String xmid);

    String getMrzpryid(Map<String,Object> map);

    void updateSjstRw(SjstRwModel sjstRwModel);

    int getSjrySjrwXmlbCount(ProjectModel model);

    List<ProjectModel> getSjrySjrwXmlbList(ProjectModel model);

    List<CodeModel> getZlzl(String xmid,String type,String yjfjlx);

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

    List<XmSx> initSXTable(String xmid);

    void addSX(XmSx model);

    void updateSX(XmSx model);

    List<CodeModel> initLYOption(String xmid);

    List<LdModel> initLDTable(String xmid);

    List<LdModel> checkLdbh(LdModel model);

    void addLD(LdModel model);

    void updateLD(LdModel model);

    void deleteFQ(String ldid, String xmid);

    void addFQ(Fqmodel model);

    List<Fqmodel> getFQData(String xmid,String id);

    CodeModel xmsqlx(String xmid);

    List<XjxqldModel> xjxqld(String xmid);

    int getWlDataCount(XtglwlkModel model);

    List<XtglwlkModel> getWlData(XtglwlkModel model);

    int pickHandleCount(XtglwlkModel model);

    List<XtglwlkModel> pickHandle(XtglwlkModel model);

    void wlHandleAdd(XmwlqdModel model);

    void wlqdHandleEdit(XmwlqdModel model);

    void wlqdHandleDelete(String id);

    List<XmwlqdModel> getWlByWlkid(String wlkid, String xmid);

    List<FjzlglModel> initStcgTable(String sjstid,String xmid,String type,String flag);

    List<SjstcgModel> stManage(SjstcgModel query);

    void stDelete(String id, int flag);

    void stcgsc(SjstcgModel model);

    List<CodeModel> getKjData();

    List<CodeModel> getCodeData(String type);

    List<CodeModel> getKhzhData();

    List<CodeModel> getKhyhData(String type);

    void addDb(XmSb model);

    void updateDb(XmSb model);

    List<XmSb> initDbpzTable(String xmId, int startRow, int endRow,String hhlxbh,String ldid);

    int initDbpzTableCount(String xmId, String hhlxbh,String ldid);

    void dbpzDelete(String id, int zt);

    List<CodeModel> initLdhb(String xmid);

    void deleteLdhb(String xmId, String ldid);

    List<XmSb> dbpzAdd(String xmid, String ldid);

    String getMph(String xmid, String ldid);

    void mphHandel(String id, String mph);

    List<WfRuInstContentEntity> queryWfRuInstContentEntity(String id);

    void deleteInst(String procInstId);

    void deleteInstContent(String procInstId);

    void updateSjstRwBz(SjstRwModel sjstRwModel);

    List<CodeModel> getzlTypeOptions();

    List<ProjectModel> getProjectLists(String xmid);

    List<Map<String,Object>> fjzlPreview(String xmid, String yjfjlx, String ejfjlx);

    int submitShow(String xmid);

    List<SjstYhzl> getXmjlQtzl(String xmid, String ejfjlx);

    List<WfTaskEntity> initHhld(String xmid, String activityid);

    void addChgzHb(XmSb model);

    List<XmSb> queryAllLdsb(Map<String, Object> pd);

    void updateMphAndSfzhByhh(Map<String, Object> pd);

    void addGW(GdModel model);

    void updateGW(GdModel model);

    void addGlg(GlgModel glgModel);

    List<Map<String,Object>> queryGdczList();

    List<Map<String,Object>> queryKjList();

    List<GdModel> getGdList(String xmid);

    List<GdModel> getGdListByid(String id);

    List<GlgModel> getGlgList(String id);

    void GWHandleDelete(String id);

    void GLGHandleDelete(String id);

    int getTcxmCount(ProjectModel model);

    List<Map<String,Object>> getTcxmtList(ProjectModel model);

    void updateXmjlsjh(String xmfzr, String xmfzr_sjh);

    void updateXmjbxx(String xmid, String xmmc, String xmdz, String lxr, String lxrsjh);

    List<Map<String,Object>> buttonshow(String activityId, int direction);

    void updatezdTask(String xmid, String activityId, String radio);

    List<SjstcgModel> ksjsfaManage(SjstcgModel query);

    List<CodeModel> getHyflCodeData(String parentid);

    void stopProject(String xmid, String zzyy);

    List<Map<String,Object>> getsqyqxx(String xmid, String activityId);

    List<Map<String,Object>> getTask(String xmid, String activityId);

    void sqyq(String id, String taskId, String jhyqjz_vue, String csyy,String yqwcsj,String sprid,String sqrid);

    List<Map<String,Object>> getActivity(String activityId);

    int getspXmlbCount(String xmbh, String spzt, String kssj, String jssj,String spr);

    List<Map<String,Object>> getspXmlb(String xmbh, String spzt, String kssj, String jssj, int startRow, int endRow,String spr);

    void passdelay(String id, String spsfty, String spyj);

    List<Map<String,Object>> getDelay(String taskid);

    List<Map<String,Object>> getxmList(String xmid);

    void updateTasksj(String jhyqsj, String id);

    List<Map<String,Object>> getRwblr(String activitiId, int direction);

    List<SjstSxpzb> initSjsx();

    List<SjstRwModel> getSjstrw(String xmid);

    List<Map<String,Object>> getRwtxfsdx(String xmid, String activitiId, int direction);

    List<Map<String,Object>> getdxList(String xmid, String activitiId, int direction,String tablename, String id);

    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    List<Map<String,Object>> getTaskRwtxfsdx(String xmid, String activitiId, int direction);

    List<SjstYhzl> getHtqdzl(String xmid, String ejfjlx);

    GetXqslEntity getXmjbxxById(String xmid);

    void insertImportFh_xqsl(Map<String, Object> pd);

    int gettoDetial_xqslCount(Map<String, Object> pd);

    List<Map<String,Object>> gettoDetial_xqslList(Map<String, Object> pd);

    void deleteFj_xqsl(String id, String userid);

    void handleAdd(ProjectModel model);

    void getSblhh(Map<String, Object> map);

    List<Map<String,Object>> getSbxx(String id);

    void updateSblhh(String id, String db);

    List<LdModel> getLdList(String ldid);

    void insertImportFh_delay(Map<String, Object> pd);

    List<Map<String,Object>> gettoDetial_delayList(String delayid);

    void deleteDelayFj(String id, String userid);

    List<Map<String,Object>> getsjstrwList(String xmid);

    List<Map<String,Object>> getdelayList(String id);

    void editChgzHb(XmSb model);

    List<CodeModel> getYsxzData();

    List<CodeModel> getHyflData();
}
