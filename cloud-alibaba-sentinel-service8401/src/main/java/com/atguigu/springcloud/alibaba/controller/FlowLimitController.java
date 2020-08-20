package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }
    @GetMapping("/testD")
    public String testD() throws InterruptedException {
        Thread.sleep(1000);
        return "-----testD";
    }
    @GetMapping("/testHotKey")
    @SentinelResource(value="testHot",blockHandler = "deal_testHotKey")
    public String testhotkey(@RequestParam(value="p1",required = false)String p1,
                             @RequestParam(value="p2",required = false)String p2){
        return "----------hotKey";
    }
    public String deal_testHotKey(String p1,String p2){
        return "deal_testHotKey/(ㄒoㄒ)/~~";
    }
}
