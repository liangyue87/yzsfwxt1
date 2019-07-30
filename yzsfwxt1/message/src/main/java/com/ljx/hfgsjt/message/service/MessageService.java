package com.ljx.hfgsjt.message.service;

import com.ljx.hfgsjt.entity.dto.message.DxDfsEntity;
import com.ljx.hfgsjt.entity.dto.message.DxFsjlEntity;

import java.util.List;
import java.util.Map;

public interface MessageService {

    List<DxDfsEntity> getDxDfsList();//查找所有的待发送短信记录
    void deleteDxDfsById(String id);//删除待发送

    void saveDxFsjl(DxFsjlEntity dxFsjlEntity);//保存到短信发送记录

    Map<String,Object> getDxMb(String id);//获取短信模板

    Map<String,Object> getUserxxById(String userid);//获取人员信息

    void dxTimer(String id);



}
