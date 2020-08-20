package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;

    @RequestMapping(value = "/payment/zk")

    public String paymentzk(){
        return "spirngcloud with zookeeper:"+serverport+"\t"+ UUID.randomUUID().toString();
    }
}
