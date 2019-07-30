package com.ljx.hfgsjt.main.serviceImpl;

import com.ljx.hfgsjt.entity.HelloTest;
import com.ljx.hfgsjt.main.service.HelloTestService;
import com.ljx.hfgsjt.repository.HelloTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloTestServiceImpl implements HelloTestService {

//    @Resource
    @Autowired
    private HelloTestRepository helloTestRepository;

    @Override
    public void save(HelloTest helloTest) {
        this.helloTestRepository.save(helloTest);
    }

    @Override
    public void delete(Long tid) {
        this.helloTestRepository.deleteById(tid);
    }

    @Override
    public List<HelloTest> findAll() {
        return this.helloTestRepository.findAll();
    }
}
