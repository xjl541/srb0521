package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.Product;
import com.atguigu.mybatisplus.mapper.ProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestVersion {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1(){
        Product pLi = productMapper.selectById(1l);

        Product pWang = productMapper.selectById(1l);

        pLi.setPrice(pLi.getPrice() + 50);
        int i = productMapper.updateById(pLi);
        System.out.println(i);

        pWang.setPrice(pWang.getPrice() - 30);
        int i1 = productMapper.updateById(pWang);
        System.out.println(i1);

        Product product = productMapper.selectById(1l);
        System.out.println(product);
    }
}
