package com.atguigu.mybatisplus.controller;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @RequestMapping("/hello")
    public void a(){
        System.out.println("hello world");
    }
}
