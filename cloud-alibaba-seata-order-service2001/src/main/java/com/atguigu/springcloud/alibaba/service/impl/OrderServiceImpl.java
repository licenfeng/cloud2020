package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDaoMapper;
import com.atguigu.springcloud.alibaba.domian.CommonResult;
import com.atguigu.springcloud.alibaba.domian.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Resource
    private OrderDaoMapper orderDaoMapper;
    @Resource
    private AccountService accountService;
    @Resource
    private StorageService storageService;
    @Override
    public void create(Order order) {
        logger.info("------------>开始新建订单");
        orderDaoMapper.create(order);
        logger.info("---------->订单微服务开始调用库存，做扣减count");
        CommonResult storageResult = storageService.decrease(order.getProductId(),order.getCount());
        logger.info("---------->订单微服务开始调用库存，做扣减count end");
        logger.info("-------------->订单微服务开始调用账户，做扣减money");
        CommonResult accountResult =accountService.decrease(order.getUserId(),order.getMoney());
        logger.info("-------------->订单微服务开始调用账户，做扣减money end");
        logger.info("---------->修改订单状态");
        orderDaoMapper.update(order.getUserId(),0);
        logger.info("----------------->修改订单状态 end");
        logger.info("-------------交易结束--------------");

    }
}
