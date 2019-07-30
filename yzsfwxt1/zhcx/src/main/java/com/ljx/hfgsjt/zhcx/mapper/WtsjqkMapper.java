package com.ljx.hfgsjt.zhcx.mapper;

import com.ljx.hfgsjt.entity.zhcx.WtsjqkModel;

import java.util.List;

/**
 * Created by Ant on 2019/5/6.
 */
public interface WtsjqkMapper {


    List<WtsjqkModel> getProjectList(WtsjqkModel model);
    int getProjectCount(WtsjqkModel model);

    /*WtsjqkModel getotherList(String id);*/
}
