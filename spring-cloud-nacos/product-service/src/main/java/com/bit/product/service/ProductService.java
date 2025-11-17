package com.bit.product.service;

import com.bit.product.ProductServiceApplication;
import com.bit.product.mapper.ProductMapper;
import com.bit.product.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;
    public ProductInfo selectProductById(Integer id) {
        return productMapper.selectProductById(id);
    }
}
