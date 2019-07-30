package com.ljx.hfgsjt.zhcx.serviceImpl;

import com.ljx.hfgsjt.entity.zhcx.SftzdlbModel;
import com.ljx.hfgsjt.zhcx.mapper.SftzdlbMapper;
import com.ljx.hfgsjt.zhcx.service.SftzdlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/7.
 */
@Service
public class SftzdlbServiceImpl implements SftzdlbService {
    @Autowired
    private SftzdlbMapper mapper;
    @Override
    public int getProjectCount(SftzdlbModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<SftzdlbModel> getProjectList(SftzdlbModel model) {
        return mapper.getProjectList(model);
    }
}
