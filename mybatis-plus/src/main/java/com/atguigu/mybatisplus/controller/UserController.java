package com.atguigu.mybatisplus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {
    @RequestMapping("/hello")
    public void a(){
        System.out.println("hello world");
    }
}
