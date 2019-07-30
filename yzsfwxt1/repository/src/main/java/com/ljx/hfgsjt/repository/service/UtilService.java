package com.ljx.hfgsjt.repository.service;

import java.util.List;
import java.util.Map;

public interface UtilService {

    List<String> queryDate(int year);

    List<String> queryBb(int year);

    //获取指定年度补班日期（周末补班）
    List<String> findWorkDate(int year);
    //获取指定年度节假日休息日期（工作日休息）
    List<String> findFreeDate(int year);

    //查询上下班时间
    Map<String,Object> findWorkTime();
}
