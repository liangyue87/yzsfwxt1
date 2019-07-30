package com.ljx.hfgsjt.zhcx.serviceImpl;

import com.ljx.hfgsjt.entity.zhcx.HtqklbModel;
import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;
import com.ljx.hfgsjt.zhcx.mapper.HtqklbMapper;
import com.ljx.hfgsjt.zhcx.service.HtqklbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/7.
 */
@Service
public class HtqklbServiceImpl  implements HtqklbService{
    @Autowired
    private HtqklbMapper mapper;
    @Override
    public int getProjectCount(HtqklbModel model) {
        return mapper.getProjectCount(model);
    }

    @Override
    public List<WtsjqkModel> getProjectList(HtqklbModel model) {
        return mapper.getProjectList(model);
    }

}
