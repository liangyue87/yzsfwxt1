package com.ljx.hfgsjt.sjst.service.Impl;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.*;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.xqsl.GetXqslEntity;
import com.ljx.hfgsjt.repository.activity.WfRuInstContentRepository;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.sjst.GlgRepository;
import com.ljx.hfgsjt.repository.sjst.GwRepository;
import com.ljx.hfgsjt.repository.sjst.XmsxRepository;
import com.ljx.hfgsjt.sjst.mapper.SjstMapper;
import com.ljx.hfgsjt.sjst.service.SjstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SjstServiceImpl implements SjstService{
    @Autowired
    private SjstMapper mapper;
    @Autowired
    private WfRuInstContentRepository repository;
    @Autowired
    private XmsxRepository smsxRepository;
    @Autowired
    private GwRepository gwRepository;
    @Autowired
    private GlgRepository glgRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Override
    public int getProjectCount(ProjectModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<ProjectModel> getProjectList(ProjectModel model) {
        return mapper.getProjectList(model);
    }

    @Override
    public List<CodeModel> getTable(String xmid,String yjfjlx) {
        return mapper.getTable(xmid,yjfjlx);
    }

    @Override
    public List<SjstYhzl> getQtzl(String xmid,String fjlx) {
        return mapper.getQtzl(xmid,fjlx);
    }

    @Override
    public void insertImportFh(SjstYhzl model) {
        mapper.insertImportFh(model);
    }

    @Override
    public void deleteFj(String id,String userid) {
        mapper.deleteFj(id,userid);
    }

    @Override
    public List<WfRuInstEntity> getInstList(String xmid, String activityId,int direction,String type) {
        return mapper.getInstList(xmid, activityId,direction,type);
    }

    @Override
    public String getPdefId(String activityId) {
        return mapper.getPdefId(activityId);
    }

    @Override
    public void updateInst(String xmid, String activitiId,int direction,String rwbh) {
        mapper.updateInst(xmid,activitiId,direction,rwbh);
    }

    @Override
    public WfTaskEntity getNowTask(String xmid, String activitiId) {
        return mapper.getNowTask(xmid,activitiId);
    }

    @Override
    public WfDfActivityEntity getActivityEntity(String activitiId) {
        return mapper.getActivityEntity(activitiId);
    }

    @Override
    public void deleteTask(String taskid) {
        mapper.deleteTask(taskid);
    }

    @Override
    public List<WfTaskEntity> getNextTaskEntity(String xmid, String activitiId,int direction) {
        return mapper.getNextTaskEntity(xmid, activitiId,direction);
    }

    @Override
    public List<WfActivityRoleEntity> getRoleList(String activityId, String userid) {
        return mapper.getRoleList(activityId,userid);
    }

    @Override
    public int getSjrwfpXmlbCount(ProjectModel model) {
        return mapper.getSjrwfpXmlbCount(model);
    }

    @Override
    public List<ProjectModel> getSjrwfpXmlbList(ProjectModel model) {
        return mapper.getSjrwfpXmlbList(model);
    }

    @Override
    public void updateTask(WfTaskEntity nowTask) {
        mapper.updateTask(nowTask);
    }

    @Override
    public List<CodeModel> getZlfl(String xmid) {
        return mapper.getZlfl(xmid);
    }


    @Override
    public List<SjstYhzl> getYhzl(String xmid, String id) {
        return mapper.getYhzl(xmid,id);
    }

    @Override
    public void saveSjstRw(SjstRwModel sjstRwModel) {
        mapper.saveSjstRw(sjstRwModel);
    }

    @Override
    public void saveXmSjstRela(XmSjstRela xmSjstRela) {
        mapper.saveXmSjstRela(xmSjstRela);
    }

    @Override
    public List<Map<String, Object>> getZpry(String roleid,String xmid) {
        return mapper.getZpry(roleid,xmid);
    }

    @Override
    public String getMrzpryid(Map<String,Object>  map) {
        return mapper.getMrzpryid(map);
    }

    @Override
    public void updateSjstRw(SjstRwModel sjstRwModel) {
        mapper.updateSjstRw(sjstRwModel);
    }

    @Override
    public int getSjrySjrwXmlbCount(ProjectModel model) {
        return mapper.getSjrySjrwXmlbCount(model);
    }

    @Override
    public List<ProjectModel> getSjrySjrwXmlbList(ProjectModel model) {
        return mapper.getSjrySjrwXmlbList(model);
    }

    @Override
    public List<CodeModel> getZlzl(String xmid,String type,String yjfjlx) {
        return mapper.getZlzl(xmid,type,yjfjlx);
    }

    @Override
    public void addTY(TyModel model) {
        mapper.addTY(model);
    }

    @Override
    public void updateTY(TyModel model) {
        mapper.updateTY(model);
    }

    @Override
    public String getKey(Map<String, Object> map) {
        return mapper.getKey(map);
    }

    @Override
    public List<TyModel> initTYTable(String xmid) {
        return mapper.initTYTable(xmid);
    }

    @Override
    public List<DxsModel> initDXSTable(String xmid) {
        return mapper.initDXSTable(xmid);
    }

    @Override
    public void addDXS(DxsModel model) {
        mapper.addDXS(model);
    }

    @Override
    public void updateDXS(DxsModel model) {
        mapper.updateDXS(model);
    }

    @Override
    public List<BfModel> initBFTable(String xmid) {
        return mapper.initBFTable(xmid);
    }

    @Override
    public void addBF(BfModel model) {
        mapper.addBF(model);
    }

    @Override
    public void updateBF(BfModel model) {
        mapper.updateBF(model);
    }

    @Override
    public List<CodeModel> initBFOption(String xmid) {
        return mapper.initBFOption(xmid);
    }

    @Override
    public List<XmSx> initSXTable(String xmid) {
        return mapper.findAllByXmid(xmid);
    }

    @Override
    public void addSX(XmSx model) {
        smsxRepository.save(model);
    }

    @Override
    public void updateSX(XmSx model) {
        mapper.updateSX(model);
    }

    @Override
    public List<CodeModel> initLYOption(String xmid) {
        return mapper.initLYOption(xmid);
    }

    @Override
    public List<LdModel> initLDTable(String xmid) {
        return mapper.initLDTable(xmid);
    }

    @Override
    public List<LdModel> checkLdbh(LdModel model) {
        return mapper.checkLdbh(model);
    }

    @Override
    public void addLD(LdModel model) {
        mapper.addLD(model);
    }

    @Override
    public void updateLD(LdModel model) {
        mapper.updateLD(model);
    }

    @Override
    public void deleteFQ(String ldid, String xmid) {
        mapper.deleteFQ(ldid,xmid);
    }

    @Override
    public void addFQ(Fqmodel model) {
        mapper.addFQ(model);
    }

    @Override
    public List<Fqmodel> getFQData(String xmid,String id) {
        return mapper.getFQData(xmid,id);
    }

    @Override
    public CodeModel xmsqlx(String xmid) {
        return mapper.xmsqlx(xmid);
    }

    @Override
    public List<XjxqldModel> xjxqld(String xmid) {
        return mapper.xjxqld(xmid);
    }

    @Override
    public int getWlDataCount(XtglwlkModel model) {
        return mapper.getWlDataCount(model);
    }

    @Override
    public List<XtglwlkModel> getWlData(XtglwlkModel model) {
        return mapper.getWlData(model);
    }

    @Override
    public int pickHandleCount(XtglwlkModel model) {
        return mapper.pickHandleCount(model);
    }

    @Override
    public List<XtglwlkModel> pickHandle(XtglwlkModel model) {
        return mapper.pickHandle(model);
    }

    @Override
    public void wlHandleAdd(XmwlqdModel model) {
        mapper.wlHandleAdd(model);
    }

    @Override
    public void wlqdHandleEdit(XmwlqdModel model) {
        mapper.wlqdHandleEdit(model);
    }

    @Override
    public void wlqdHandleDelete(String id) {
        mapper.wlqdHandleDelete(id);
    }

    @Override
    public List<XmwlqdModel> getWlByWlkid(String wlkid, String xmid) {
        return mapper.getWlByWlkid(wlkid,xmid);
    }

    @Override
    public List<FjzlglModel> initStcgTable(String sjstid,String xmid,String type,String flag) {
        return mapper.initStcgTable(sjstid,xmid,type,flag);
    }

    @Override
    public List<SjstcgModel> stManage(SjstcgModel query) {
        return mapper.stManage(query);
    }

    @Override
    public void stDelete(String id, int flag) {
        mapper.stDelete(id,flag);
    }

    @Override
    public void stcgsc(SjstcgModel model) {
        mapper.stcgsc(model);
    }

    @Override
    public List<CodeModel> getKjData() {
        return mapper.getKjData();
    }

    @Override
    public List<CodeModel> getCodeData(String type) {
        return mapper.getCodeData(type);
    }

    @Override
    public List<CodeModel> getKhzhData() {
        return mapper.getKhzhData();
    }

    @Override
    public List<CodeModel> getKhyhData(String type) {
        return mapper.getKhyhData(type);
    }

    @Override
    public void addDb(XmSb model) {
        mapper.addDb(model);
    }

    @Override
    public void updateDb(XmSb model) {
        mapper.updateDb(model);
    }

    @Override
    public List<XmSb> initDbpzTable(String xmId, int startRow, int endRow,String hhlxbh,String ldid) {
        return mapper.initDbpzTable(xmId,startRow,endRow,hhlxbh,ldid);
    }

    @Override
    public int initDbpzTableCount(String xmId, String hhlxbh,String ldid) {
        return mapper.initDbpzTableCount(xmId,hhlxbh,ldid);
    }

    @Override
    public void dbpzDelete(String id, int zt) {
        mapper.dbpzDelete(id,zt);
    }

    @Override
    public List<CodeModel> initLdhb(String xmid) {
        return mapper.initLdhb(xmid);
    }

    @Override
    public void deleteLdhb(String xmId, String ldid) {
        mapper.deleteLdhb(xmId,ldid);
    }

    @Override
    public List<XmSb> dbpzAdd(String xmid, String ldid) {
        return mapper.dbpzAdd(xmid,ldid);
    }

    @Override
    public String getMph(String xmid, String ldid) {
        return mapper.getMph(xmid, ldid);
    }

    @Override
    public void mphHandel(String id, String mph) {
        mapper.mphHandel(id, mph);
    }

    @Override
    public List<WfRuInstContentEntity> queryWfRuInstContentEntity(String id) {
        return mapper.queryWfRuInstContentEntity(id);
    }

    @Override
    public void deleteInst(String procInstId) {
        mapper.deleteInst(procInstId);
    }

    @Override
    public void deleteInstContent(String procInstId) {
        mapper.deleteInstContent(procInstId);
    }

    @Override
    public void updateSjstRwBz(SjstRwModel sjstRwModel) {
        mapper.updateSjstRwBz(sjstRwModel);
    }

    @Override
    public List<CodeModel> getzlTypeOptions() {
        return mapper.getzlTypeOptions();
    }

    @Override
    public List<ProjectModel> getProjectLists(String xmid) {
        return mapper.getProjectLists(xmid);
    }

    @Override
    public List<Map<String, Object>> fjzlPreview(String xmid, String yjfjlx, String ejfjlx) {
        return mapper.fjzlPreview(xmid, yjfjlx, ejfjlx);
    }

    @Override
    public int submitShow(String xmid) {
        return mapper.submitShow(xmid);
    }

    @Override
    public List<SjstYhzl> getXmjlQtzl(String xmid, String ejfjlx) {
        return mapper.getXmjlQtzl(xmid, ejfjlx);
    }

    @Override
    public List<WfTaskEntity> initHhld(String xmid, String activityid) {
        return mapper.initHhld(xmid, activityid);
    }

    @Override
    public void addChgzHb(XmSb model) {
        mapper.addChgzHb(model);
    }

    @Override
    public List<XmSb> queryAllLdsb(Map<String, Object> pd) {
        return mapper.queryAllLdsb(pd);
    }

    @Override
    public void updateMphAndSfzhByhh(Map<String, Object> pd) {
        mapper.updateMphAndSfzhByhh(pd);
    }

    @Override
    public void addGW(GdModel model) {
        gwRepository.save(model);
    }

    @Override
    public void updateGW(GdModel model) {
        gwRepository.save(model);
    }

    @Override
    public void addGlg(GlgModel glgModel) {
        glgRepository.save(glgModel);
    }

    @Override
    public List<Map<String, Object>> queryGdczList() {
        return mapper.queryGdczList();
    }

    @Override
    public List<Map<String, Object>> queryKjList() {
        return mapper.queryKjList();
    }

    @Override
    public List<GdModel> getGdList(String xmid) {
        return mapper.getGdList(xmid);
    }

    @Override
    public List<GdModel> getGdListByid(String id) {
        return mapper.getGdListByid(id);
    }

    @Override
    public List<GlgModel> getGlgList(String id) {
        return mapper.getGlgList(id);
    }

    @Override
    public void GWHandleDelete(String id) {
        mapper.GWHandleDelete(id);
    }

    @Override
    public void GLGHandleDelete(String id) {
        mapper.GLGHandleDelete(id);
    }

    @Override
    public int getTcxmCount(ProjectModel model) {
        return mapper.getTcxmCount(model);
    }

    @Override
    public List<Map<String, Object>> getTcxmtList(ProjectModel model) {
        return mapper.getTcxmtList(model);
    }

    @Override
    public void updateXmjlsjh(String xmfzr, String xmfzr_sjh) {
        mapper.updateXmjlsjh(xmfzr, xmfzr_sjh);
    }

    @Override
    public void updateXmjbxx(String xmid, String xmmc, String xmdz, String lxr, String lxrsjh) {
        mapper.updateXmjbxx(xmid, xmmc, xmdz, lxr, lxrsjh);
    }

    @Override
    public List<Map<String, Object>> buttonshow(String activityId, int direction) {
        return mapper.buttonshow(activityId,direction);
    }

    @Override
    public void updatezdTask(String xmid, String activityId, String radio) {
        mapper.updatezdTask(xmid,activityId,radio);
        mapper.updaterwb(xmid,radio);
    }

    @Override
    public List<SjstcgModel> ksjsfaManage(SjstcgModel query) {
        return mapper.ksjsfaManage(query);
    }

    @Override
    public List<CodeModel> getHyflCodeData(String parentid) {
        return mapper.getHyflCodeData(parentid);
    }

    @Override
    public void stopProject(String xmid, String zzyy) {
        mapper.stopProject(xmid, zzyy);
    }

    @Override
    public List<Map<String, Object>> getsqyqxx(String xmid, String activityId) {
        return mapper.getsqyqxx(xmid, activityId);
    }

    @Override
    public List<Map<String, Object>> getTask(String xmid, String activityId) {
        return mapper.getTask(xmid, activityId);
    }

    @Override
    public void sqyq(String id, String taskId, String jhyqjz_vue, String csyy,String yqwcsj,String sprid,String sqrid) {
        mapper.sqyq(id, taskId, jhyqjz_vue,csyy,yqwcsj,sprid,sqrid);
    }

    @Override
    public List<Map<String, Object>> getActivity(String activityId) {
        return mapper.getActivity(activityId);
    }

    @Override
    public int getspXmlbCount(String xmbh, String spzt, String kssj, String jssj,String spr) {
        return mapper.getspXmlbCount(xmbh, spzt,  kssj, jssj,spr);
    }

    @Override
    public List<Map<String, Object>> getspXmlb(String xmbh, String spzt, String kssj, String jssj, int startRow, int endRow,String spr) {
        return mapper.getspXmlb(xmbh, spzt, kssj,jssj, startRow,endRow,spr);
    }

    @Override
    public void passdelay(String id, String spsfty, String spyj) {
        mapper.passdelay(id, spsfty, spyj);
    }

    @Override
    public List<Map<String, Object>> getDelay(String taskid) {
        return mapper.getDelay(taskid);
    }

    @Override
    public List<Map<String, Object>> getxmList(String xmid) {
        return mapper.getxmList(xmid);
    }

    @Override
    public void updateTasksj(String jhyqsj, String id) {
        mapper.updateTasksj(jhyqsj, id);
    }

    @Override
    public List<Map<String, Object>> getRwblr(String activitiId, int direction) {
        return mapper.getRwblr(activitiId,direction);
    }

    @Override
    public List<SjstSxpzb> initSjsx() {
        return mapper.initSjsx();
    }

    @Override
    public List<SjstRwModel> getSjstrw(String xmid) {
        return mapper.getSjstrw(xmid);
    }

    @Override
    public List<Map<String, Object>> getRwtxfsdx(String xmid, String activitiId, int direction) {
        return mapper.getRwtxfsdx(xmid,activitiId,direction);
    }

    @Override
    public List<Map<String, Object>> getdxList(String xmid, String activitiId, int direction,String tablename, String id) {
        return mapper.getdxList(xmid,activitiId,direction,tablename,id);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }

    @Override
    public List<Map<String, Object>> getTaskRwtxfsdx(String xmid, String activitiId, int direction) {
        return mapper.getTaskRwtxfsdx(xmid,activitiId,direction);
    }

    @Override
    public List<SjstYhzl> getHtqdzl(String xmid, String ejfjlx) {
        return mapper.getHtqdzl(xmid,ejfjlx);
    }

    @Override
    public GetXqslEntity getXmjbxxById(String xmid) {
        return mapper.getXmjbxxById(xmid);
    }

    @Override
    public void insertImportFh_xqsl(Map<String, Object> pd) {
        mapper.insertImportFh_xqsl(pd);
    }

    @Override
    public int gettoDetial_xqslCount(Map<String, Object> pd) {
        return mapper.gettoDetial_xqslCount(pd);
    }

    @Override
    public List<Map<String, Object>> gettoDetial_xqslList(Map<String, Object> pd) {
        return mapper.gettoDetial_xqslList(pd);
    }

    @Override
    public void deleteFj_xqsl(String id, String userid) {
        mapper.deleteFj_xqsl(id,userid);
    }

    @Override
    public void handleAdd(ProjectModel model) {
        mapper.handleAdd(model);
    }

    @Override
    public void getSblhh(Map<String, Object> map) {
        mapper.getSblhh(map);
    }

    @Override
    public List<Map<String,Object>> getSbxx(String id) {
        return mapper.getSbxx(id);
    }

    @Override
    public void updateSblhh(String id, String db) {
        mapper.updateSblhh(id,db);
    }

    @Override
    public List<LdModel> getLdList(String ldid) {
        return mapper.getLdList(ldid);
    }

    @Override
    public void insertImportFh_delay(Map<String, Object> pd) {
        mapper.insertImportFh_delay(pd);
    }

    @Override
    public List<Map<String, Object>> gettoDetial_delayList(String delayid) {
        return mapper.gettoDetial_delayList(delayid);
    }

    @Override
    public void deleteDelayFj(String id, String userid) {
        mapper.deleteDelayFj(id,userid);
    }

    @Override
    public List<Map<String, Object>> getsjstrwList(String xmid) {
        return mapper.getsjstrwList(xmid);
    }

    @Override
    public List<Map<String, Object>> getdelayList(String id) {
        return mapper.getdelayList(id);
    }

    @Override
    public void editChgzHb(XmSb model) {
        mapper.editChgzHb(model);
    }

    @Override
    public List<CodeModel> getYsxzData() {
        return mapper.getYsxzData();
    }

    @Override
    public List<CodeModel> getHyflData() {
        return mapper.getHyflData();
    }

}
