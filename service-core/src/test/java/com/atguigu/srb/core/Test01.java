package com.atguigu.srb.core;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test01 {

    @Test
    public void test(){
        // 1.创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 2. 全局参数
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Helen");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setSwagger2(true);//开启Swagger2模式
        autoGenerator.setGlobalConfig(gc);
        // 3. 数据源
        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setUrl("jdbc:mysql://localhost:3306/srb_core?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSource);
        // 4. 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.atguigu.srb.core");
        pc.setEntity("pojo.entity"); //此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
        autoGenerator.setPackageInfo(pc);
        // 5. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略

        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategyConfig.setEntityLombokModel(true); // lombok
        strategyConfig.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀（确保tinyint(1)）
        strategyConfig.setRestControllerStyle(true); //restful api风格控制器
        autoGenerator.setStrategy(strategyConfig);
        // 6. 执行代码生成器
        autoGenerator.execute();
    }

}
