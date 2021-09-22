package com.atguigu.srb.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为swagger2
                .groupName("adminApi") // 用于定义api文档汇总信息
                .apiInfo(adminApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("尚融宝后台管理系统-API文档") // 文章页标题
                .description("本文档描述了尚融宝后台管理系统接口") // 详细信息
                .version("1.0") // 文章版本号
                .contact(new Contact("xiexie", "http://atguigu.com", "1057363663@qq.com"))
                .build();
    }
}
