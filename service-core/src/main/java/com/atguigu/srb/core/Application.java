package com.atguigu.srb.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.atguigu.srb.core.mapper")
@ComponentScan({"com.atguigu.srb.core","com.atguigu.srb.base","com.atguigu.srb.common"})  // 导入依赖默认在根目录下，包名相同内容求交集
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
