package com.ljx.hfgsjt.yj.serviceImpl;

import com.ljx.hfgsjt.entity.yj.XmLd;
import com.ljx.hfgsjt.yj.dao.LdMapper;
import com.ljx.hfgsjt.yj.service.LdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ant on 2019/5/9.
 */
@Service
public class LdServiceImpl implements LdService {
    @Autowired
    private LdMapper mapper;

    @Override
    public int getLdCount(String xmid) {
        return mapper.getLdCount(xmid);
    }

    @Override
    public List<XmLd> getLdData(String xmid, int startRow, int endRow) {
        return mapper.getLdData(xmid,startRow,endRow);
    }

    @Override
    public List<XmLd> LddzjData(String id) {
        return mapper.LddzjData(id);
    }

    @Override
    public List<XmLd> LdhxData(String id) {
        return mapper.LdhxData(id);
    }
}
