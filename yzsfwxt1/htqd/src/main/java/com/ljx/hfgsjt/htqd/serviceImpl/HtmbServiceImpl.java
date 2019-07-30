package com.ljx.hfgsjt.htqd.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.htqd.DemoHtbzformEntity;
import com.ljx.hfgsjt.entity.dto.htqd.XtglHtmbEntity;
import com.ljx.hfgsjt.htqd.service.HtmbService;
import com.ljx.hfgsjt.repository.activity.*;
import com.ljx.hfgsjt.repository.htqd.HtbzRepository;
import com.ljx.hfgsjt.repository.htqd.XtghHtmbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HtmbServiceImpl implements HtmbService {

    @Autowired
    private HtbzRepository repository;

    @Autowired
    private WfRuInstRepository wfRuInstRepository;

    @Autowired
    private WfTaskRepository wfTaskRepository;

    @Autowired
    private WfTaskHistoryRepository wfTaskHistoryRepository;

    @Autowired
    private WfReNodeRepository nodeRepository;

    @Autowired
    private WfDfActivityRepository activityRepository;

    @Autowired
    private XtghHtmbRepository xtghHtmbRepository;

    @Override
    public void save(DemoHtbzformEntity entity) {
        repository.save(entity);
    }

    @Override
    public Integer findMaxIdFromRu_inst() {
        return wfRuInstRepository.findByMaxId();
    }

    @Override
    public void saveWfRuInst(WfRuInstEntity wfRuInstEntity) {
        wfRuInstRepository.save(wfRuInstEntity);
    }

    @Override
    public Integer findMaxIdFromTask() {
        return wfTaskRepository.findByMaxId();
    }

    @Override
    public void savewfTaskHistory(WfTaskHistoryEntity wfTaskHistoryEntity) {
        wfTaskHistoryRepository.save(wfTaskHistoryEntity);
    }

    @Override
    public void savewfTask(WfTaskEntity wfTaskEntity) {
        wfTaskRepository.save(wfTaskEntity);
    }

    @Override
    public List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction) {
        return nodeRepository.findByActivityidAndDirection(activityid,direction);
    }

    @Override
    public WfDfActivityEntity findByActivityid(String activityid) {
        return activityRepository.findByActivityid(activityid);
    }

    @Override
    public List<WfRuInstEntity> findByPdefid(String pdef_id) {
        return wfRuInstRepository.findByPdefId(pdef_id);
    }

    @Override
    public List<WfTaskEntity> findByProcInstId(String pdef_id) {
        return wfTaskRepository.findByProcInstId(pdef_id);
    }

    @Override
    public Page<XtglHtmbEntity> findXtglHtmb(String mbmc, PageRequest request) {
        return xtghHtmbRepository.findXtglHtmb(mbmc,request);
    }

    @Override
    public void saveHtmb(XtglHtmbEntity xtglHtmbEntity) {
        xtghHtmbRepository.save(xtglHtmbEntity);
    }

    @Override
    public void deleteHtmbById(String wybs) {
        xtghHtmbRepository.deleteById(wybs);
    }

}
