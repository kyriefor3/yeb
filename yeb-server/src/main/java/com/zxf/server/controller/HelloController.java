package com.zxf.server.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @ApiOperation("测试用")
    @GetMapping("hello")
    public String hello(){
        return "hello";
    }

    @ApiOperation("测试用")
    @GetMapping("/employee/basic/**")
    public String hello1(){
        return "basic/aa";
    }

    @ApiOperation("测试用")
    @GetMapping("/employee/advance/**")
    public String hello2(){
        return "advance/aa";
    }
}
