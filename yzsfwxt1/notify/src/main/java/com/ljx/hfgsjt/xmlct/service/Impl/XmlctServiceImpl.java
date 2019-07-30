package com.ljx.hfgsjt.xmlct.service.Impl;

import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.sfkp.SfkpSftzdzbEntity;
import com.ljx.hfgsjt.xmlct.mapper.XmlctMapper;
import com.ljx.hfgsjt.xmlct.service.XmlctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class XmlctServiceImpl implements XmlctService {

    @Autowired
    private XmlctMapper  xmlctMapper;

    @Override
    public List<TaskEntity> getXmlctDqjd(String xmid) {
        return xmlctMapper.getXmlctDqjd(xmid);
    }

    @Override
    public List<TaskEntity> getXmlctLsjd(String xmid) {
        return xmlctMapper.getXmlctLsjd(xmid);
    }

    @Override
    public List<TaskEntity> selectXmlctDq(String xmid, String pdefid) {
        return xmlctMapper.selectXmlctDq(xmid,pdefid);
    }

    @Override
    public List<TaskEntity> selectXmlctLs(String xmid, String pdefid) {
        return xmlctMapper.selectXmlctLs(xmid,pdefid);
    }

    @Override
    public int getXmlbCount(XmInstance xmInstance) {
        return xmlctMapper.getXmlbCount(xmInstance);
    }

    @Override
    public List<XmInstance> getXmlbList(XmInstance xmInstance) {
        return xmlctMapper.getXmlbList(xmInstance);
    }

    @Override
    public List<SfkpSftzdzbEntity> getSfkpList(String xmid) {
        return xmlctMapper.getSfkpList(xmid);
    }

    @Override
    public Map<String, Object> getRoleidAndOrgidAndBmfzridByUserid(String userid) {
        return xmlctMapper.getRoleidAndOrgidAndBmfzridByUserid(userid);
    }

    @Override
    public Map<String, Object> getXmmcByid(String xmid) {
        return xmlctMapper.getXmmcByid(xmid);
    }
}
