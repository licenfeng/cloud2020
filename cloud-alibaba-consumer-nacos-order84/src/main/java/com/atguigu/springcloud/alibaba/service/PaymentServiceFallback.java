package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

//统一为service方法里面的异常进行处理
@Component

public class PaymentServiceFallback implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级返回payment FallBackService",new Payment(id,"errorSerial"));
    }
}
