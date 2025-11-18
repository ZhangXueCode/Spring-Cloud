package com.bit.order.controller;
import com.bit.order.api.ProductApi;
import com.bit.product.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private ProductApi productApi;

    @RequestMapping("/o1")
    public String o1(Integer id) {
        return productApi.p1(id);
    }

    @RequestMapping("/o2")
    public String o2(Integer id,String name) {
        return productApi.p2(id,name);
    }

    @RequestMapping("/o3")
    public String o3() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("zhangsan");
        productInfo.setId(1);
        return productApi.p3(productInfo);
    }

    @RequestMapping("/o4")
    public String o1() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductName("lisi");
        productInfo.setId(2);
        return productApi.p4(productInfo);
    }

}
