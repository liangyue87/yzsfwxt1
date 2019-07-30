package com.ljx.hfgsjt.wtys.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Sjst.XmInstance;
import com.ljx.hfgsjt.entity.wtys.YsZjqtFkEntity;
import com.ljx.hfgsjt.repository.yswt.YsZjqtFkRepository;
import com.ljx.hfgsjt.wtys.dao.YsZjqtMapper;
import com.ljx.hfgsjt.wtys.service.YsZjqtFkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class YsZjqtFkServiceImpl implements YsZjqtFkService {

    @Autowired
    private YsZjqtFkRepository ysZjqtFkRepository;

    @Autowired
    private YsZjqtMapper dao;

    @Override
    public Page<YsZjqtFkEntity> getYsZjqtFkList(Pageable pageable) {
        return ysZjqtFkRepository.findAll(pageable);
    }

    @Override
    public void upoadFile(Map<String, Object> pd) {
        dao.upoadFile(pd);
    }

    @Override
    public void saveYsZjqtFk(Map<String, Object> pd) {
        dao.saveYsZjqtFk(pd);
    }

    @Override
    public List<Map<String,Object>> findTaskByRefid(Map<String, Object> pd) {
        return dao.findTaskByRefid(pd);
    }

    @Override
    public List<Map<String,Object>> findRuInstByPid(Map<String, Object> pd) {
        return dao.findRuInstByPid(pd);
    }

    @Override
    public void insertWfHiInst(Map<String,Object> map) {
        dao.insertWfHiInst(map);
    }

    @Override
    public void insertWfHiInstContent(Map<String, Object> pd) {
        dao.insertWfHiInstContent(pd);
    }

    @Override
    public void deleteWfRuInst(Map<String, Object> pd) {
        dao.deleteWfRuInst(pd);
    }

    @Override
    public void deleteWfRuInstContent(Map<String, Object> pd) {
        dao.deleteWfRuInstContent(pd);
    }

    @Override
    public void deleteTask(Map<String, Object> pd) {
        dao.deleteTask(pd);
    }

    @Override
    public List<Map<String, Object>> queryZjqtzbById(Map<String, Object> pd) {
        return dao.queryZjqtzbById(pd);
    }

    @Override
    public List<Map<String,Object>> queryBmById(Map<String, Object> pd) {
        return dao.queryBmById(pd);
    }

    @Override
    public List<Map<String, Object>> queryZjqtcbById(Map<String, Object> pd) {
        return dao.queryZjqtcbById(pd);
    }

    @Override
    public List<Map<String, Object>> queryZjqtfjById(Map<String, Object> pd) {
        return dao.queryZjqtfjById(pd);
    }

    @Override
    public void deleteFjByid(Map<String, Object> pd) {
        dao.deleteFjByid(pd);
    }

    @Override
    public List<XmInstance> getXmxxByid(String xmid) {
        return dao.getXmxxByid(xmid);
    }

    @Override
    public List<String> queryPara(String xmid) {
        return dao.queryPara(xmid);
    }

    @Override
    public List<String> queryFkByzbid(String zbid) {
        return dao.queryFkByzbid(zbid);
    }

    @Override
    public List<String> queryParas(String xmid) {
        return dao.queryParas(xmid);
    }

    @Override
    public List<Map<String,Object>> queryFkByzbids(String zbid) {
        return dao.queryFkByzbids(zbid);
    }


}
