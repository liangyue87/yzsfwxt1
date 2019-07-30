package com.ljx.hfgsjt.repository.servicrImpl;

import com.ljx.hfgsjt.repository.dao.UtilMapper;
import com.ljx.hfgsjt.repository.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UtilServiceImpl  implements UtilService {

    @Autowired
    private UtilMapper dao;

    @Override
    public List<String> queryDate(int year) {
        return dao.queryDate(year);
    }

    @Override
    public List<String> queryBb(int year) {
        return dao.queryBb(year);
    }

    @Override
    public List<String> findWorkDate(int year) {
        return dao.findWorkDate(year);
    }

    @Override
    public List<String> findFreeDate(int year) {
        return dao.findFreeDate(year);
    }

    @Override
    public Map<String, Object> findWorkTime() {
        return dao.findWorkTime();
    }
}
