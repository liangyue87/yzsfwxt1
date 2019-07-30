package com.ljx.hfgsjt.zhcx.serviceImpl;


import com.ljx.hfgsjt.entity.dto.BaseEntity;
import com.ljx.hfgsjt.entity.zhcx.zhyslb;
import com.ljx.hfgsjt.zhcx.mapper.ZhysMapper;
import com.ljx.hfgsjt.zhcx.service.ZhysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ZhysServiceImpl implements ZhysService {

    @Autowired
    ZhysMapper zhysMapper;





    @Override
    public List<zhyslb> selectZhysList(String xmmc, Integer page, Integer size, Integer yszt) {


        if (null ==  page) {
            page = 0;
        }
        if (size==null) {
            size = 10;
        }
        Integer start=page*size;
        Integer end=page*size+size;
        return zhysMapper.selectZhysList(xmmc,start,end,yszt);
    }

    @Override
    public List<Map<String, Object>> getSbxxList(BaseEntity baseEntity) {
        return zhysMapper.getSbxxList(baseEntity);
    }

    @Override
    public int getSbxxTotal(BaseEntity baseEntity) {
        return zhysMapper.getSbxxTotal(baseEntity);
    }
}
