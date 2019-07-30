package com.ljx.hfgsjt.wtys.serviceImpl;

import com.ljx.hfgsjt.entity.dto.Activity.*;
import com.ljx.hfgsjt.entity.dto.UserEntity;
import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtcbEntity;
import com.ljx.hfgsjt.entity.wtys.YsZjqtzbEntity;
import com.ljx.hfgsjt.repository.activity.*;
import com.ljx.hfgsjt.repository.message.DxDfsRepository;
import com.ljx.hfgsjt.repository.yswt.YsZjqtcbRepository;
import com.ljx.hfgsjt.repository.yswt.YsZjqtzbRepository;
import com.ljx.hfgsjt.wtys.service.YsZjqtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YsZjqtServiceImpl implements YsZjqtService {

    @Autowired
    private YsZjqtzbRepository ysZjqtzbRepository;

    @Autowired
    private YsZjqtcbRepository ysZjqtcbRepository;

    @Autowired
    private WfRuInstRepository instRepository;
    @Autowired
    private WfRuInstContentRepository instContentRepository;
    @Autowired
    private WfDfActivityRepository activityRepository;
    @Autowired
    private WfReNodeRepository nodeRepository;
    @Autowired
    private WfTaskRepository taskRepository;
    @Autowired
    private WfTaskHistoryRepository taskHistoryRepository;
    @Autowired
    private DxDfsRepository dxDfsRepository;

    @Override
    public List<UserEntity> getQtcyry(String xmid) {
        return null;
    }

    @Override
    public String saveYszjqt(YsZjqtzbEntity ysZjqtzbEntity) {
        ysZjqtzbRepository.saveAndFlush(ysZjqtzbEntity);
        return "success";
    }

    @Override
    public void saveWfRuInst(WfRuInstEntity e) {
        instRepository.save(e);
    }

    @Override
    public void saveWfTaskHistory(WfTaskHistoryEntity e) {
        taskHistoryRepository.save(e);
    }

    @Override
    public void saveWfTask(WfTaskEntity e) {
        taskRepository.save(e);
    }

    @Override
    public void saveWfRuInstContent(WfRuInstContentEntity e) {
        instContentRepository.save(e);
    }

    @Override
    public WfDfActivityEntity findByActivityid(String activityid) {

        return activityRepository.findByActivityid(activityid);
    }

    @Override
    public List<WfReNodeEntity> findByActivityidAndDirection(String activityid, String direction) {

        return nodeRepository.findByActivityidAndDirection(activityid,direction);
    }

    @Override
    public int findMaxIdToWfRuInst() {
        return instRepository.findByMaxId();
    }

    @Override
    public int findMaxIdToWfTask() {
        return taskRepository.findByMaxId();
    }

    @Override
    public void saveYszjqtcb(YsZjqtcbEntity ysZjqtcbEntity) {
        ysZjqtcbRepository.saveAndFlush(ysZjqtcbEntity);
    }

    @Override
    public void saveDxDfsEntity(DxDfsEntity dxDfsEntity) {
        dxDfsRepository.saveAndFlush(dxDfsEntity);
    }
}
