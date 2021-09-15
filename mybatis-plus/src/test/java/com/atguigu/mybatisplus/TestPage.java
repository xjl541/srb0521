package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestPage {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        IPage<User> userIPage = new Page<>(2,5);
        IPage<User> page = userMapper.selectPage(userIPage, null);
        System.out.println(page);
    }

    @Test
    public void test2(){
        Page<User> page = new Page<>(1,2);
        Page<User> page1 = new Page<>();
        Integer age = 10;
        IPage<User> users = userMapper.selectPageByPage(page1, age);
        System.out.println(users);
    }
}
