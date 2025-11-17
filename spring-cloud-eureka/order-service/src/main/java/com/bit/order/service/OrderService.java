package com.bit.order.service;

import com.bit.order.mapper.OrderMapper;
import com.bit.order.model.OrderInfo;
import com.bit.order.model.ProductInfo;
import jakarta.annotation.PostConstruct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    private List<ServiceInstance> instances;
    @PostConstruct
    public void init() {
        instances = discoveryClient.getInstances("product-service");
    }
    public OrderInfo selectById(Integer orderId) {
        OrderInfo orderInfo =  orderMapper.selectById(orderId);
//        String url = "http://127.0.0.1:9090/product/" + orderInfo.getProductId();
        String url = "http://product-service/product/" + orderInfo.getProductId();
        ProductInfo productInfo = restTemplate.getForObject(url,ProductInfo.class);
        orderInfo.setProductInfo(productInfo);
        return orderInfo;
    }
}
