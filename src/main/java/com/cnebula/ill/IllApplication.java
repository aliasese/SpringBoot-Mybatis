package com.cnebula.ill;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@MapperScan(basePackages = "com.cnebula.ill.m*")
@SpringBootApplication
@EnableScheduling
public class IllApplication {

    public static void main(String[] args) {
        SpringApplication.run(IllApplication.class, args);
    }

    //ÅäÖÃmybatisµÄ·ÖÒ³²å¼þpageHelper
    //@Bean
    /*public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        properties.setProperty("dialect","postgresql");    //ÅäÖÃmysqlÊý¾Ý¿âµÄ·½ÑÔ
        pageHelper.setProperties(properties);
        return pageHelper;
    }*/
}
