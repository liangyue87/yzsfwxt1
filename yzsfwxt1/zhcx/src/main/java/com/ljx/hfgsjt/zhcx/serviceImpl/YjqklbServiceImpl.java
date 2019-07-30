package com.ljx.hfgsjt.zhcx.serviceImpl;

import com.ljx.hfgsjt.entity.zhcx.YjqklbModel;
import com.ljx.hfgsjt.zhcx.mapper.YjqklbMapper;
import com.ljx.hfgsjt.zhcx.service.YjqklbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/8.
 */
@Service
public class YjqklbServiceImpl implements YjqklbService {
    @Autowired
    private YjqklbMapper mapper;

    @Override
    public int getProjectCount(YjqklbModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<YjqklbModel> getProjectList(YjqklbModel model) {
        return mapper.getProjectList(model);
    }
}
