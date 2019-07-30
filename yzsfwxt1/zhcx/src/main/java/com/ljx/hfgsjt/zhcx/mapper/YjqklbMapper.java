package com.ljx.hfgsjt.zhcx.mapper;

import com.ljx.hfgsjt.entity.zhcx.YjqklbModel;

import java.util.List;

/**
 * Created by Ant on 2019/5/8.
 */
public interface YjqklbMapper {
    int getProjectCount(YjqklbModel model);

    List<YjqklbModel> getProjectList(YjqklbModel model);
}
