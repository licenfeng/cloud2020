package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Value("${server.port}")
    private String serverport;
    @Resource
    private PaymentService paymentService;
    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_ok(id);
        logger.info(result);
        return result;
    }

    @GetMapping("payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_timeOutHandler",
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_timeOut(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_timeOut(id);
        logger.info(result);
        return result;
    }

    public String paymentInfo_timeOutHandler(@PathVariable("id") Integer id){
        return "系统繁忙，服务端降级啦";
    }


    //熔断
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        logger.info(result);
        return result;
    }

    @GetMapping(value="/payment/lb")
    public String getPaymentLB(){
        return serverport;
    }
}
