package com.ljx.hfgsjt.main.serviceImpl;


import com.ljx.hfgsjt.entity.dto.XtglWkzbEntity;
import com.ljx.hfgsjt.main.dao.WorkdayMapper;
import com.ljx.hfgsjt.main.service.WorkdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkdayServiceImpl implements WorkdayService {

    @Autowired
    WorkdayMapper mapper;


    @Override
    public List<Map<String, Object>> getDaysList() {
        return mapper.getDaysList();
    }

    @Override
    public List<XtglWkzbEntity> getWorkTime() {
        return mapper.getWorkTime();
    }

    @Override
    public void saveDays(Map map) {
        mapper.saveDays(map);
    }

    @Override
    public void updateDays(Map map) {
        mapper.updateDays(map);
    }

    @Override
    public void deleteDays(String id) {
        mapper.deleteDays(id);
    }

    @Override
    public void saveTime(XtglWkzbEntity xtglWkzbEntity) {
        mapper.saveTime(xtglWkzbEntity);
    }

    @Override
    public void updateTime(XtglWkzbEntity xtglWkzbEntity) {
        mapper.updateTime(xtglWkzbEntity);
    }

    @Override
    public void deleteTime(String id) {
        mapper.deleteTime(id);
    }
}
