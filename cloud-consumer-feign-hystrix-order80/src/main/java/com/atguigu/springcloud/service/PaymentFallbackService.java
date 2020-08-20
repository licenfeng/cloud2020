package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * 优雅的解决服务降级，解决高耦合
 */
@Component
public class PaymentFallbackService implements PaymenthystrixService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "-----paymentFallbackService fall back ";
    }

    @Override
    public String paymentInfo_timeOut(Integer id) {
        return "-----paymentFallbackService fall back ";
    }
}
