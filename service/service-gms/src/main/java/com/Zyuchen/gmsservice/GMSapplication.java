package com.Zyuchen.gmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Zyuchen"})
@EnableDiscoveryClient
@EnableFeignClients
public class GMSapplication {

    public static void main(String[] args){
        SpringApplication.run(GMSapplication.class,args);
    }
}
