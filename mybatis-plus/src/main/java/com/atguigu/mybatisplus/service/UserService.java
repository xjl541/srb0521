package com.atguigu.mybatisplus.service;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {
    public List<User> selectAllBuName(String name);
}
