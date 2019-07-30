package com.ljx.hfgsjt.main.serviceImpl;


import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.main.dao.WorkMapper;
import com.ljx.hfgsjt.main.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    WorkMapper mapper;


    @Override
    public List<Map<String, String>> getTypeCheckbox() {
        return mapper.getTypeCheckbox();
    }

    @Override
    public List<Map<String,Object>> getMyBacklog(BaseEntity baseEntity) {
        return mapper.getMyBacklog(baseEntity);
    }

    @Override
    public int getMyBacklogTotal(BaseEntity baseEntity) {
        return mapper.getMyBacklogTotal(baseEntity);
    }

    @Override
    public List<Map<String, Object>> getMyDone(BaseEntity baseEntity) {
        return mapper.getMyDone(baseEntity);
    }

    @Override
    public int getMyDoneTotal(BaseEntity baseEntity) {
        return mapper.getMyDoneTotal(baseEntity);
    }

    @Override
    public List<Map<String, Object>> getMyOriginate(BaseEntity baseEntity) {
        return mapper.getMyOriginate(baseEntity);
    }

    @Override
    public int getMyOriginateTotal(BaseEntity baseEntity) {
        return mapper.getMyOriginateTotal(baseEntity);
    }

    @Override
    public List<Map<String, Object>> getXmData(String userid) {
        return mapper.getXmData(userid);
    }

    @Override
    public Map<String, Object> getHhldData(String userid) {
        return mapper.getHhldData(userid);
    }

    @Override
    public List<Map<String, Object>> getAllInstInfo(String xmid, String c_activityid) {
        return mapper.getAllInstInfo(xmid,c_activityid);
    }

    @Override
    public List<Map<String, Object>> getAllInstInfoNum(String xmid) {
        return mapper.getAllInstInfoNum(xmid);
    }

    @Override
    public List<Map<String, Object>> getMessageList(String userid) {
        return mapper.getMessageList(userid);
    }
}
