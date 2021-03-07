package com.Zyuchen.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.Zyuchen"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ServiceMsmApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceMsmApplication.class, args);
    }
}
