package com.ljx.hfgsjt.xmlct.service;

import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;

import java.util.List;
import java.util.Map;

public interface XmlctService {

    List<TaskEntity> getXmlctDqjd(String xmid);
    List<TaskEntity> getXmlctLsjd(String xmid);

    List<TaskEntity> selectXmlctDq( String xmid,  String pdefid);
    List<TaskEntity> selectXmlctLs( String xmid,  String pdefid);

    int getXmlbCount(XmInstance xmInstance);
    List<XmInstance> getXmlbList(XmInstance xmInstance);

    List<SfkpSftzdzbEntity>getSfkpList(String xmid);

    Map<String, Object> getRoleidAndOrgidAndBmfzridByUserid(String userid);

    Map<String, Object> getXmmcByid(String xmid);
}
