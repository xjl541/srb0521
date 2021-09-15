package com.atguigu.mybatisplus.service.impl;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServcieImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> selectAllBuName(String name) {
        return baseMapper.selectAllName(name);
    }
}
