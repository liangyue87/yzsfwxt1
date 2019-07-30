package com.ljx.hfgsjt.sgjg.service;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.Sjst.CodeModel;
import com.ljx.hfgsjt.entity.dto.Sjst.GdModel;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.htqd.HtQdzbEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sgjg.GetXmlbSgEntity;
import com.ljx.hfgsjt.entity.sgjg.GetsgxtzbEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwJlfwEntity;
import com.ljx.hfgsjt.entity.sgjg.SgxtRwbEntity;

import java.util.List;
import java.util.Map;

public interface SgxdService {

    /**
     * 数据条数
     * @return
     */
    int getTotal();

    List<GetsgxtzbEntity> getSggcdList(Map<String, Object> pd);

    List<GetsgxtzbEntity> getAllSggcdList(Map<String, Object> pd);

    List<Map<String, Object>> queryWjjlbByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryDbByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryHbByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryWlxxByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryXdfwJtByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryLdJtByXmid(Map<String, Object> pd);

    void hbSubmit(Map<String, Object> pd);

    void hbEditSubmit(Map<String, Object> pd);

    void dbSubmit(Map<String, Object> pd);

    void dbEditSubmit(Map<String, Object> pd);

    void wbSubmit(Map<String, Object> pd);

    void wbEditSubmit(Map<String, Object> pd);

    List<Map<String,Object>> findXmfw(Map<String, Object> pd);

    void dbdelete(Map<String, Object> pd);

    void wbdelete(Map<String, Object> pd);

    void saveSgxtRwb(SgxtRwbEntity sgxtRwbEntity);

    void saveSgxtRwjl(SgxtRwJlfwEntity sgxtRwJlfwEntity);

    void saveWfRuInst(WfRuInstEntity instEntity);

    void saveWfRuInstContent(WfRuInstContentEntity wfRuInstContentEntity);

    void saveWfTaskHistory(WfTaskHistoryEntity historyEntity);

    List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction);

    WfDfActivityEntity findByActivityid(String activityid);

    void saveWfTask(WfTaskEntity task);

    List<Map<String,Object>> queryAllWjjlbByXmid(Map<String, Object> pd);

    List<Map<String,Object>> queryAllDbByXmid(Map<String, Object> pd);

    List<Map<String,Object>> queryAllHbByXmid(Map<String, Object> pd);

    List<Map<String,Object>> queryAllWlxxByXmid(Map<String, Object> pd);

    List<Map<String,Object>> queryJlfwByXmid(Map<String, Object> pd);

    List<Map<String, Object>> querySgxdByXmid(Map<String, Object> pd);

    List<Map<String, Object>> queryPdefId(String id,String lcscId);

    List<Map<String, Object>> queryPdefIdAndBmmc(String id);

    WfTaskEntity findBycActivityidAndProcInstId(String cActivityid,String procInstId);

    List<WfTaskEntity> findByProcInstId(String procInstId);

    void deleteTask(String pdefId,String procInstId);

    void deleteTasks(String pdefId,String procInstId,String refid);

    WfRuInstEntity findByprocInstId(String procInstId);

    void savewfHiInst(WfHiInstEntity wfHiInstEntity);

    void deleteInst(String procInstId);

    WfRuInstContentEntity findByProcInstIdAndContentname(String procInstId, String id);

    void saveWfHiInstContent(WfHiInstContentEntity wfHiInstContentEntity);

    void deleteInstContent(String procInstId, String id);

    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    WfTaskEntity findBycActivityidAndRefid(String pdefId, String refid);

    void updateShzt(Map<String, Object> pd);

    void updateShth(Map<String, Object> pd);

    void updateZmgzry(Map<String, Object> pd);

    void updateRwzt(Map<String, Object> pd);

    String texuChange(Map<String, Object> map);

    void updateWfruInst(String procInstId, String pdef_id);

    String getUserIdByActivityId(String activityId);//通过流程定义ID获取到UserId

    List<Map<String, Object>> getSgrwdById(String sgdid);

    List<GetXmlbSgEntity> getSgxdsList(Map<String, Object> pd);

    List<GetXmlbSgEntity> getAllSgxdList(Map<String, Object> pd);

    List<WfReNodeEntity> findByActivityidAndNextnode(String activityid, String nextnode);

    List<String> queryGxsbidBySgdId(Map<String, Object> pd);

    List<Map<String,Object>> querySbByLdid(String ldid);

    List<Map<String, Object>> queryxmxxByid(String xmid);

    List<Map<String, Object>> querySqlxByXmid(Map<String, Object> pd);

    String getUserIdByroleId(String roleId);

    List<String> queryRwblr(String actId, String id, String procInstId);

    List<XmInstance> getXmxxByid(String xmid);

    UserEntity getUserByUserId(String userid);

    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    void wbEditdelete(Map<String, Object> pd);

    void dbEditdelete(Map<String, Object> pd);

    void hbEditdelete(Map<String, Object> pd);

    List<Map<String, Object>> queryWjjlbByXmids(Map<String, Object> pd);

    List<Map<String, Object>> queryDbByXmids(Map<String, Object> pd);

    List<Map<String, Object>> queryHbByXmids(Map<String, Object> pd);

    List<HtQdzbEntity> getHtxx(String xmid);

    List<UserEntity> getUserxx(String xmjl);

    WfTaskEntity findByTaskid(String taskId);

    List<CodeModel> initLdhb(String xmid);

    List<XmSb> initDbpzTable(String xmId,String sgdid, String ldid);

    List<XmSb> initDbpzTables(String xmId, String sgdid, String ldid);

    List<String> querySgdidByxmid(String xmid);

    void deleteSgsb(String sgdid);

    List<GdModel> getGdList(String xmid,String sgdid);


    void gwdelete(Map<String, Object> pd);

    void insertGw(Map<String, Object> pd);
}
