package com.ljx.hfgsjt.yj.service;

import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.yj.*;

import java.util.List;
import java.util.Map;

public interface SbyjService {

    List<azlxEntity> findAzlxBySllx(Map<String, Object> pd);


    int getTotal();

    List<GetXmlbSbyjEntity> getSbyjList(String xmbh, String xmmc, String gcdz,
                                         String azlx, Integer page, Integer size);

    List<GetXmlbSbyjEntity> getSbyjListBysj(Map<String, Object> pd);

    List<GetXmlbSbyjEntity> queryAllSbyjLists(Map<String, Object> pd);

    List<YjSbyjzbEntity> findByXmid(String xmid);

    List<XmSb> queryWblist(Map<String, Object> pd);

    List<XmSb> queryGsblist(Map<String, Object> pd);

    List<XmSb> queryAllWblists(Map<String, Object> pd);

    List<XmSb> queryAllGsblists(Map<String, Object> pd);

    List<Map<String, Object>> getSbcjList(Map<String, Object> pd);

    List<Map<String, Object>> getZbkjList(Map<String, Object> pd);

    List<Map<String, Object>> getqyList(Map<String, Object> pd);

    List<Map<String, Object>> getsblxList(Map<String, Object> pd);

    List<Map<String, Object>> getXmxxByXmid(Map<String, Object> pd);

    void editWb(WjjlbEntity entity);

    List<Map<String, Object>> queryJMblist(Map<String, Object> pd);

    List<Map<String, Object>> queryAllJmblists(Map<String, Object> pd);

    List<Map<String, Object>> getpqList(Map<String, Object> pd);

    List<XmSb> findJmbByLdid(Map<String, Object> pd);

    void elxedit(Map<String, Object> pd);

    void pledit(Map<String, Object> pd);

    void updateBsmAndFqByBzh(Map<String, Object> pd);

    void sbyjLdSubmit(Map<String, Object> pd);

    void sbyjDbSubmit(Map<String, Object> pd);

    void saveSbyjzb(Map<String, Object> pd);

    void deteleHbByYjdbh(Map<String, Object> pd);

    void deleteHbByLdid(Map<String, Object> pd);

    List<String> queryhbidByLdid(Map<String, Object> pd);

    void ldhbSubmit(Map<String, Object> pd);

    void updateCbhb(Map<String, Object> pd);

    List<Map<String, Object>> getZpry(Map<String, Object> pd);

    List<Map<String, Object>> shWblist(Map<String, Object> pd);

    List<Map<String, Object>> shAllWblists(Map<String, Object> pd);

    List<Map<String, Object>> shGsblist(Map<String, Object> pd);

    List<Map<String, Object>> shAllGsblists(Map<String, Object> pd);

    List<Map<String, Object>> shJMblist(Map<String, Object> pd);

    List<Map<String, Object>> shAllJmblists(Map<String, Object> pd);

    List<Map<String, Object>> getYsxz(Map<String, Object> pd);

    void sheditsb(Map<String, Object> pd);

    List<Map<String, Object>> getHyflCodeData(String ysxz);

    List<Map<String, Object>> shJmbByLdid(Map<String, Object> pd);

    void shpledit(Map<String, Object> pd);

    List<Map<String, Object>> getZpcby(Map<String, Object> pd);

    void updateCby(Map<String, Object> pd);

    void cbyeditsb(Map<String, Object> pd);

    void cbypledit(Map<String, Object> pd);

    void updateSbztByid(Map<String, Object> pd);

    void deleteDb(Map<String, Object> pd);

    void deleteLdsb(Map<String, Object> pd);

    String findZbidBybh(Map<String, Object> pd);

    void sheditsbs(Map<String, Object> pd);

    List<String> queryRwblr(String activityId, String refId,String procInstId);

    List<String> querycbyThRwblr(String activityId, String refId,String procInstId);

    List<String> querySbids(String xmid, String refId);

    void cbysbdr(Map<String, Object> map);

    List<Map<String, Object>> querydcsb(Map<String, Object> pd);

    List<Map<String, Object>> queryAlldcsb(Map<String, Object> pd);

    String querysbcjjcBybh(String sbcj);

    String querysbkjmcBybh(String kjbh);

    List<Map<String,Object>> querysfkp(BaseEntity e);

    int querysfkpTotal(BaseEntity e);

    List<Map<String,Object>> querysgxd(BaseEntity e);

    int querysgxdTotal(BaseEntity e);

    void gsplbj(Map<String, Object> pd);

    void deleteWb(Map<String, Object> pd);

    void sbyjWbSubmit(Map<String, Object> pd);
}
