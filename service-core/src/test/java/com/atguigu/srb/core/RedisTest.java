package com.atguigu.srb.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void save() {
        redisTemplate.opsForValue().set("k1", "v1");
    }

    @Test
    public void getData(){
        Object k1 = redisTemplate.opsForValue().get("k1");
        System.out.println(k1);
    }
}
