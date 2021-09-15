package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    public List<User> selectAllName(String name);
}
