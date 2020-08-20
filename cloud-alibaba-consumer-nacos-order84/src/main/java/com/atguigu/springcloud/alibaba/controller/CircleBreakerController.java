package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.alibaba.handler.MyHandler;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.constraints.Null;
@RestController
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    public String url;
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value="fallback",
            fallbackClass = MyHandler.class,fallback = "fallbackDemo",
            blockHandlerClass = MyHandler.class,
            blockHandler = "handlerDemo")
    public CommonResult<Payment>fallback(@PathVariable("id") Long id){
        CommonResult<Payment>result = restTemplate.getForObject(url+"/paymentSQL/"+id,CommonResult.class,id);
        if(id==4){
            throw new IllegalArgumentException("非法参数异常。。。");
        }else if(result.getData()==null){
            throw new NullPointerException("NullPointException ,没有对应记录");
        }
        return result;
    }

    @GetMapping(value = "/consumer/paymentSQL/{id}")
    @SentinelResource(value="paymentSQL",
            fallbackClass = MyHandler.class,fallback = "fallbackDemo",
            blockHandlerClass = MyHandler.class,
            blockHandler = "handlerDemo")
    public CommonResult<Payment>paymentSQL(@PathVariable("id")Long id){
        return paymentService.paymentSQL(id);
    }

//    public CommonResult handler1(@PathVariable("id") Long id, HandlerException e){
////        Payment payment = new Payment(id,null);
////        return new CommonResult(4433,"兜底handler，exception："+e.getMessage(),payment);
////    }

}
