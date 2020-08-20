package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import io.micrometer.core.instrument.Meter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {
    @Resource
    private PaymentService paymentService;
    @GetMapping(value="/consumer/payment/get/{id}")
    public CommonResult<Payment>getPaymentById(@PathVariable("id")Long id){
        return paymentService.getPaymentById(id);
    }
    @GetMapping(value="payment/feign/timeout")
    public String paymentFeignTimeOut(){
        return paymentService.paymentFeignTimeOut();
    }
}
