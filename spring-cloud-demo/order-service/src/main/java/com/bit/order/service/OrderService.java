package com.bit.order.service;

import com.bit.order.mapper.OrderMapper;
import com.bit.order.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    public OrderInfo selectById(Integer orderId) {
        return orderMapper.selectById(orderId);
    }
}
