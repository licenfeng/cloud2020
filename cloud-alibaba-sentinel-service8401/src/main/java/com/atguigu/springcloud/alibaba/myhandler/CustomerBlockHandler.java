package com.atguigu.springcloud.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public class CustomerBlockHandler {
    public static CommonResult handlerException1(BlockException BlockException){
        return new CommonResult(4411,"global handlerException 按照客户自定义---1",
                new Payment(2020L,"serial1003"));

    }

    public static CommonResult handlerException2(BlockException BlockException){
        return new CommonResult(4422,"global handlerException 按照客户自定义---2",
                new Payment(2020L,"serial1003"));

    }
}
