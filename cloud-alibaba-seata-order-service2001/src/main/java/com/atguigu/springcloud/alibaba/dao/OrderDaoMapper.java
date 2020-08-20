package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domian.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDaoMapper {
    //创建订单
    void create(Order order);
    //修改订单状态，0->1
    void update(@Param("userId")Long userId,@Param("status")Integer status);
}
