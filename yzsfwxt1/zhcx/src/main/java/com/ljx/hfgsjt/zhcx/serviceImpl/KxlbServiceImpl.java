package com.ljx.hfgsjt.zhcx.serviceImpl;


import com.ljx.hfgsjt.entity.zhcx.Wtysqk;
import com.ljx.hfgsjt.zhcx.mapper.KxlbMapper;
import com.ljx.hfgsjt.zhcx.service.KxlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KxlbServiceImpl implements KxlbService {

    @Autowired
    KxlbMapper kxlbMapper;


    @Override
    public List<Wtysqk> selectKxlbList() {
        return kxlbMapper.selectKxlbList();
    }
}
