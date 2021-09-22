package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestWrapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        // 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("name","a")
                .isNotNull("email")
                .between("age",10,20);
        List<User> users = userMapper.selectList(queryWrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        // 按年龄降序查询用户，如果年龄相同则按id升序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("age")
                .orderByDesc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    public void test3(){
        // 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper
                .like("name","n")
                .and(i ->i.lt("age",18).or().isNull("email"));
        List<User> users = userMapper.selectList(queryWrapper);
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void test4(){
        // 查询所有用户的用户名和年龄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age");
        List<User> list = userMapper.selectList(queryWrapper);
        System.out.println(list);
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        System.out.println(maps);
    }

    @Test
    public void test5(){
        //查询id不大于3的所有用户的id列表
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.inSql("id","select id from user where id <=3");
        List<User> list = userMapper.selectList(queryWrapper);
        for (User user : list) {
            System.out.println(user);
        }
    }
}
