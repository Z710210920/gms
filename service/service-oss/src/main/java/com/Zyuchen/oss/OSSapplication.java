package com.Zyuchen.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//oss类不需要启动数据库，在此处禁止数据库配置启动
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.Zyuchen")
public class OSSapplication {
    public static void main(String[] args){
        SpringApplication.run(OSSapplication.class,args);
    }
}
             
