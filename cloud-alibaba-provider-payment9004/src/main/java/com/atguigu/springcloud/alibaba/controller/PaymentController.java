package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverport;
    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static{
        hashMap.put(1L,new Payment(1L,"qwerty111"));
        hashMap.put(2L,new Payment(1L,"qwerty222"));
        hashMap.put(3L,new Payment(1L,"qwerty333"));
    }
    @GetMapping(value = "/paymentSQL/{id}")
    public CommonResult<Payment>paymentSQL(@PathVariable("id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment>result = new CommonResult<>(200,"from mysql server port："+serverport,payment);
        return result;
    }
}
