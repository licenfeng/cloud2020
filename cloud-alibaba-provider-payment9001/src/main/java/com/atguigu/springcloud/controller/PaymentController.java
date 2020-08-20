package com.atguigu.springcloud.controller;

import io.micrometer.core.instrument.Meter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value="/payment/nacos/{id}")
    public String getpayment(@PathVariable("id")Integer id){
        return "nacos registy ,server port"+port +"\t"+ id;
    }
}
