package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.discovery.DiscoveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class PaymentController {
    Logger log = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverport;
    @Autowired
    public PaymentService paymentService;
    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果8002======"+result);
        if(result>0){
            return new CommonResult<Payment>(200,"插入成功serverport："+serverport+"---"+payment.getId()+",hahaha");
        }else {
            return new CommonResult<Payment>(500,"插入失败serverport"+serverport);
        }

    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果8002======"+payment);
        if(payment!=null){
            return new CommonResult<Payment>(200,"查询成功serverport,id"+serverport+"---"+id,payment);
        }else {
            return new CommonResult<Payment>(500,"查询失败serverport,id"+serverport+"---"+id,null);
        }
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLB(){
        return serverport;
    }

    @GetMapping(value="/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverport;
    }

}
