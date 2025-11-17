package com.bit.order.service;

import com.bit.order.mapper.OrderMapper;
import com.bit.order.model.OrderInfo;
import com.bit.order.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    public OrderInfo selectById(Integer orderId) {
        OrderInfo orderInfo =  orderMapper.selectById(orderId);
        String url = "http://product-service/product/" + orderInfo.getProductId();
        ProductInfo productInfo = restTemplate.getForObject(url,ProductInfo.class);
        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }
}
