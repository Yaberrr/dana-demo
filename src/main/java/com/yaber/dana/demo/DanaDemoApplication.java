package com.yaber.dana.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.yaber.dana.demo.feign" )
public class DanaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanaDemoApplication.class, args);
    }




}
