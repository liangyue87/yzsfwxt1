package com.ljx.hfgsjt.zhcx.service;

import com.ljx.hfgsjt.entity.zhcx.SftzdlbModel;

import java.util.List;

/**
 * Created by Ant on 2019/5/7.
 */
public interface SftzdlbService {
    int getProjectCount(SftzdlbModel model);

    List<SftzdlbModel> getProjectList(SftzdlbModel model);
}
