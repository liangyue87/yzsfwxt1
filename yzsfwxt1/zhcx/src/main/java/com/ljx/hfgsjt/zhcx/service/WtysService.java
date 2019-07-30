package com.ljx.hfgsjt.zhcx.service;


import com.ljx.hfgsjt.entity.zhcx.Wtysqk;

import java.util.List;

public interface WtysService {      //引用发起官网信息数据列表视图（视图表格字段相同）
    List<Wtysqk> selectWtysList(String xm, Integer page, Integer size, Integer wtyszt);//数据集合
}
