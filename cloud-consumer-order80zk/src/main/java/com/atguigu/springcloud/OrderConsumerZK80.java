package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderConsumerZK80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderConsumerZK80.class,args);
    }
}
