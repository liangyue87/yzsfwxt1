package com.ljx.hfgsjt.js.service.Impl;

import com.ljx.hfgsjt.entity.dto.Sjst.InstEntity;
import com.ljx.hfgsjt.entity.dto.Sjst.TaskEntity;
import com.ljx.hfgsjt.entity.dto.js.*;
import com.ljx.hfgsjt.js.mapper.JsspCsMapper;
import com.ljx.hfgsjt.js.service.JssqCsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JssqCsServiceImpl implements JssqCsService {

    @Autowired
    private JsspCsMapper jsspCsMapper;


    @Override
    public int selectJssqCsCount(Jssq jssq) {
        return jsspCsMapper.selectJssqCsCount(jssq);
    }

    @Override
    public List<Jssq> selectJssqCsList(Jssq jssq) {
        return jsspCsMapper.selectJssqCsList(jssq);
    }

    @Override
    public ys_rucb selectYshz(String flbh, String xmid) {
        return jsspCsMapper.selectYshz(flbh,xmid);
    }
    @Override
    public JsRwCb selectJshz(String flbh, String xmid) {
        return jsspCsMapper.selectJshz(flbh,xmid);
    }

    @Override
    public int selectJszlJcCount(String xmid) {
        return jsspCsMapper.selectJszlJcCount(xmid);
    }

    @Override
    public List<JsRwFj> selectJszlJcList(Map<String, Object> map) {
        return jsspCsMapper.selectJszlJcList(map);
    }

    @Override
    public void updataRwzb(JsRwZb jsRwZb) {
        jsspCsMapper.updataRwzb(jsRwZb);
    }

    @Override
    public JsRwZb selectRwzb(String xmid) {
        return jsspCsMapper.selectRwzb(xmid);
    }

    @Override
    public InstEntity getRuInst(String xmid) {
        return jsspCsMapper.getRuInst(xmid);
    }

    @Override
    public TaskEntity getTask(String xmid) {
        return jsspCsMapper.getTask(xmid);
    }

    @Override
    public void insertTask(TaskEntity taskEntity) {
        jsspCsMapper.insertTask(taskEntity);
    }

    @Override
    public void insertHiTask(TaskEntity taskEntity) {
        jsspCsMapper.insertHiTask(taskEntity);
    }

    @Override
    public List<JsRwFj> selectJsFjList(String xmid) {
        return jsspCsMapper.selectJsFjList(xmid);
    }

    @Override
    public void deleteGwxxCb(String zbid) {
        jsspCsMapper.deleteGwxxCb(zbid);
    }
    @Override
    public void deleteGwxxFj(String zbid) {
        jsspCsMapper.deleteGwxxFj(zbid);
    }

    @Override
    public void deleteGwxxFj_sh(String xmid, String fjlx) {
        jsspCsMapper.deleteGwxxFj_sh(xmid,fjlx);
    }

    @Override
    public void deleteGwxxZb(String xmid) {
        jsspCsMapper.deleteGwxxZb(xmid);
    }

    @Override
    public InstEntity selectRuInst(String flbh, String xmid) {
        return jsspCsMapper.selectRuInst(flbh,xmid);
    }

    @Override
    public void delectTask(String taskid) {
        jsspCsMapper.delectTask(taskid);
    }
}
