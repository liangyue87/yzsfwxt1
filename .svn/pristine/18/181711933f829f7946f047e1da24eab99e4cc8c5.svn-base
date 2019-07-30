package com.ljx.hfgsjt.zhcx.serviceImpl;

import com.ljx.hfgsjt.entity.zhcx.HtqklbModel;
import com.ljx.hfgsjt.entity.zhcx.TftzdlbModel;
import com.ljx.hfgsjt.zhcx.mapper.TftzdlbMapper;
import com.ljx.hfgsjt.zhcx.service.TftzdlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/7.
 */
@Service
public class TftzdlbServiceImpl implements TftzdlbService {
    @Autowired
    private TftzdlbMapper mapper;

    @Override
    public int getProjectCount(TftzdlbModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<TftzdlbModel> getProjectList(TftzdlbModel model) {
        return mapper.getProjectList(model);
    }
}
