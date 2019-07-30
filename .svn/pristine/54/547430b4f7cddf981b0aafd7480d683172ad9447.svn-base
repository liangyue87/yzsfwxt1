package com.ljx.hfgsjt.sfkp.service;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.dto.Sjst.XmSb;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.XmInstanceEntity;
import com.ljx.hfgsjt.entity.dto.htqd.GetXmlbJfEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.entity.sfkp.SfkpldEntity;

import java.util.List;
import java.util.Map;

public interface SfkpService {

    List<Map<String, Object>> getSftzd(Map<String,Object> pd);

    List<Map<String, Object>> getSkzh();

    List<Map<String, Object>> getGcflm();

    void insertSftzdcb(Map<String, Object> cbpd);

    void insertSftzdzb(Map<String, Object> pd);

    void updateSftzd(Map<String, Object> pd);

    List<GetXmlbJfEntity> getJfList(Map<String, Object> pd);

    List<GetXmlbJfEntity> getAllJfList(Map<String, Object> pd);

    List<Map<String, Object>> getZbkjList();

    List<XmSb> queryGsbByXmid(Map<String, Object> pd);

    List<XmSb> queryAllGsbByXmid(Map<String, Object> pd);

    List<XmSb> queryGsbByXmids(Map<String, Object> pd);

    List<XmSb> queryAllGsbByXmids(Map<String, Object> pd);

    void updateSbgcfBySbid(Map<String, Object> pd);

    void gspldjxg(Map<String, Object> pd);

    List<Map<String, Object>> gsjezj(Map<String, Object> pd);

    List<Map<String, Object>> hbjezj(Map<String, Object> pd);

    List<SfkpldEntity> queryLdByxmid(String xmid);

    List<Map<String, Object>> querymxByxmid(String xmid);

    List<XmSb> querySbByldid(String xmid, String ldid);

    void insertSftzdcbsb(Map<String, Object> pd);

    void deleteSfsbByldid(Map<String, Object> pd);

    List<XmSb> queryHb(Map<String, Object> pd);

    List<XmSb> queryallHb(Map<String, Object> pd);

    List<XmSb> queryAllHb(Map<String, Object> pd);

    List<Map<String, Object>> queryLd(Map<String, Object> pd);

    void updateJfcbgcfBySbid(Map<String, Object> pd);

    void deleteJfhb();

    List<Map<String, Object>> gssbje(Map<String, Object> pd);

    void saveJfgssb(Map<String, Object> pd);

    List<String> querybhBymc(Map<String, Object> cbpd);

    void updateJfcbgsb(Map<String, Object> cbpd);

    void updateJfcbhb(Map<String, Object> cbpd);

    void deleteJfgsb();

    List<SfkpSftzdzbEntity> queryByTzdid(String tzdid);

    List<Map<String, Object>> queryCbByTzdid(String tzdid);

    void deleteSftzdcb(Map<String, Object> pd);

    void updateSftzdzb(Map<String, Object> pd);

    List<Map<String, Object>> querylxByname(Map<String, Object> pd);

    List<Map<String, Object>> querylxBynames(Map<String, Object> pd);

    List<Map<String, Object>> querypjlx();

    void importPz(Map<String, Object> pd);

    List<XmInstanceEntity> findXmByid(Map<String, Object> pd);

    /**
     * 缴费列表
     * 获取基本信息
     * @param xmid
     * @return
     */
    List<XmInstanceEntity> getjbxx(String xmid);

    List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd);

    void deleteFjByid(Map<String, Object> pd);

    UserEntity getUserByUserId(String userid);

    /**
     * 发送短信
     * @param dxDfsEntity
     */
    void saveDxDfsEntity(DxDfsEntity dxDfsEntity);

    void ExecAfterJfqr(Map<String, Object> map);

    List<Map<String, Object>> querySfzbByid(String zbid);

    List<XmInstance> getxmdz(String xmid);

    List<String> queryGsCbid(String id);

    List<String> queryJmCbid(String id);

    void deleteSftzdcbsb(String cbid,String sblx);

    List<String> querygssbid(String cbid);

    List<String> queryjmsbid(String cbid);

    void updategssbcbid(String sbid,String cbid);

    void updatejmsbcbid(String sbid,String cbid);

    List<XmInstance> findXmByxmid(String xmid);

    List<Map<String, Object>> findSftzd(String xmid);

    List<Map<String, Object>> getGsbTotalByXmid(String xmid, String id);
}
