package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymenthystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "fallbackMethod",commandProperties =
        {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
public class OrderHystrixController {
    @Resource
    private PaymenthystrixService paymenthystrixService;
    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
       String result = paymenthystrixService.paymentInfo_ok(id);
       return result;
    }

    @GetMapping("consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler",
//            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @HystrixCommand   //使用全局，放在类头部
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){
        String result = paymenthystrixService.paymentInfo_timeOut(id);
        return result;
    }
    public String paymentInfo_timeOutHandler(@PathVariable("id") Integer id){
        return "系统繁忙，客户端降级啦";
    }
    //全局fallback方法
    public String fallbackMethod(){
        return "Global return fallback";
    }
}
