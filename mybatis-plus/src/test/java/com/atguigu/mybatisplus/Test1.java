package com.atguigu.mybatisplus;


import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test01(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test02(){
        User user = userMapper.selectById(5l);
        System.out.println(user);
    }

    @Test
    public void save(){
        User user = new User();
        user.setName("helen");
        user.setAge(18);
        user.setEmail("123456@qq.com");
        int i = userMapper.insert(user);
        System.out.println(i);
    }
    @Test
    public void delete(){
        int i = userMapper.deleteById(1437740471794274306l);
        System.out.println(i);
    }
    @Test
    public void update(){
        User user = new User();
        user.setId(1l);
        user.setAge(28);
        int i = userMapper.updateById(user);
    }
    @Test
    public void query(){
        User user = userMapper.selectById(2l);
        System.out.println(user);
    }
    @Test
    public void query2(){
        Map map = new HashMap();
        map.put("name","Jack");
        map.put("age",20);
        List list = userMapper.selectByMap(map);
        System.out.println(list);
    }

    @Test
    public void query3(){
        List<Integer> list = new ArrayList<>();
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<User> users = userMapper.selectBatchIds(list1);
        System.out.println(users);
    }
}
