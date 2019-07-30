package com.ljx.hfgsjt.zhcx.serviceImpl;


import com.ljx.hfgsjt.entity.zhcx.Wtysqk;
import com.ljx.hfgsjt.zhcx.mapper.WtysMapper;
import com.ljx.hfgsjt.zhcx.service.WtysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class WtysServiceImpl implements WtysService {

    @Autowired
    WtysMapper wtysMapper;



    @Override
    public List<Wtysqk> selectWtysList(String xm, Integer page, Integer size, Integer wtyszt) {
        if (page == null) {
            page = 0;
        }
        if (size==null) {
            size = 10;
        }
        Integer start=page*size;
        Integer end=page*size+size;

        return wtysMapper.selectWtysList(xm,start,end,wtyszt);
    }

}
