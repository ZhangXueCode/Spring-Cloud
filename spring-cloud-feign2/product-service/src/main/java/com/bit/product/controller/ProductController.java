package com.bit.product.controller;

import com.bit.product.ProductServiceApplication;
import com.bit.product.model.ProductInfo;
import com.bit.product.service.ProductService;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/{productId}")
    public ProductInfo getProductById(@PathVariable("productId") Integer productId) {
        log.info("接收参数 productId：" + productId);
        return productService.selectProductById(productId);
    }

    @RequestMapping("/p1")
    public String p1(Integer id) {
        return "接收到参数 id：" + id;
    }

    @RequestMapping("/p2")
    public String p2(Integer id,String name) {
        return "接收到参数 id：" + id + " name: " + name;
    }

    @RequestMapping("/p3")
    public String p3(ProductInfo productInfo) {
        return "接收到参数 productInfo：" + productInfo;
    }

    @RequestMapping("/p4")
    public String p4(@RequestBody ProductInfo productInfo) {
        return "接收到参数 productInfo：" + productInfo;
    }


}
