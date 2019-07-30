package com.ljx.hfgsjt.main.service;

import com.ljx.hfgsjt.entity.HelloTest;

import java.util.List;

public interface HelloTestService {

    public void save(HelloTest helloTest);

    public void delete(Long id);

    public List<HelloTest> findAll();
}
