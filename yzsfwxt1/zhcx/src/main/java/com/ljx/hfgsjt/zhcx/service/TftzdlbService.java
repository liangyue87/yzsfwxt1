package com.ljx.hfgsjt.zhcx.service;

import com.ljx.hfgsjt.entity.zhcx.TftzdlbModel;

import java.util.List;

/**
 * Created by Ant on 2019/5/7.
 */
public interface TftzdlbService {
    int getProjectCount(TftzdlbModel model);

    List<TftzdlbModel> getProjectList(TftzdlbModel model);
}
