package com.Zyuchen.gmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Zyuchen"})
public class GMSapplication {

    public static void main(String[] args){
        SpringApplication.run(GMSapplication.class,args);
    }
}
