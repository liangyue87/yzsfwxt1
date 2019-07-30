package com.ljx.hfgsjt.main.controller;

import com.ljx.hfgsjt.main.service.HelloWorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")
@Api("swaggerDemoController相关api")
public class IndexController {
    @Autowired
    private HelloWorldService helloWorldService;

    @ApiOperation(value = "测试使用", notes = "测试具体信息")
    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return helloWorldService.test();
    }
}
