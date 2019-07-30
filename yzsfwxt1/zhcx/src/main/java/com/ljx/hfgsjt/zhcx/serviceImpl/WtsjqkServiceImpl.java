package com.ljx.hfgsjt.zhcx.serviceImpl;

import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;
import com.ljx.hfgsjt.zhcx.mapper.WtsjqkMapper;
import com.ljx.hfgsjt.zhcx.service.WtsjqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/6.
 */
@Service
public class WtsjqkServiceImpl implements WtsjqkService {
    @Autowired
    private WtsjqkMapper wtmapper;

    @Override
    public int getProjectCount(WtsjqkModel model) {
        return wtmapper.getProjectCount(model);
    }

    @Override
    public List<WtsjqkModel> getProjectList(WtsjqkModel model) {
        return wtmapper.getProjectList(model);
    }

   /* @Override
    public WtsjqkModel getotherList(String id) {
        return wtmapper.getotherList(id);
    }*/
}
