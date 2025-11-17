package com.bit.product.controller;

import com.bit.product.ProductServiceApplication;
import com.bit.product.model.ProductInfo;
import com.bit.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
}
