package com.atguigu.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

public class MyHandler {
    public static CommonResult handlerDemo(Long id, BlockException blockException){
        Payment payment = new Payment(id,null);
        return new CommonResult(4433,"兜底handler，exception："+blockException.getMessage(),payment);
    }
    public static CommonResult fallbackDemo(Long id,Throwable throwable){
        Payment payment = new Payment(id,null);
        return new CommonResult(4444,"兜底fallback，exception："+throwable.getMessage(),payment);
    }
}
