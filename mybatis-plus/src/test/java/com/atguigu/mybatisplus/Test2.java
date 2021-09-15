package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.service.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test2 {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void test1(){
        User user = new User();
        user.setName("李四");
        user.setAge(18);
        boolean b = userService.save(user);
        System.out.println(b);
    }

    @Test
    public void test2(){
        boolean b = userService.removeById(1437748977729626114L);
        System.out.println(b);
    }

    @Test
    public void test3(){
        User user = new User();
        user.setName("张三");
        user.setId(1l);
        userService.updateById(user);
    }
    @Test
    public void test4(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setName("helen" + i);
            user.setAge(18);
            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }

    @Test
    public void test5(){
        String name = "helen";
        List<User> users = userMapper.selectAllName(name);
        System.out.println(users);
    }

    @Test
    public void test6(){
        String name = "helen";
        List<User> users = userService.selectAllBuName(name);
        System.out.println(users);
    }
}
