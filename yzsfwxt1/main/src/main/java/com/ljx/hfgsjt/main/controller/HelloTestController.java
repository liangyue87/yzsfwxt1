package com.ljx.hfgsjt.main.controller;

import com.ljx.hfgsjt.entity.HelloTest;
import com.ljx.hfgsjt.main.service.HelloTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "HelloTest")
public class HelloTestController {
    @Autowired
    private HelloTestService helloTestService;

    //添加
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(){
        HelloTest helloTest = new HelloTest();
        helloTest.setName("张三");
        helloTest.setTcode("001");
        this.helloTestService.save(helloTest);
    }

    //删除
    @RequestMapping(value = "/delete/{tid}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long tid){
        this.helloTestService.delete(tid);
    }

    //查询所有
    @RequestMapping(value = "getAll",method = RequestMethod.GET)
    public List<HelloTest> findAll(){
        return this.helloTestService.findAll();
    }
}
