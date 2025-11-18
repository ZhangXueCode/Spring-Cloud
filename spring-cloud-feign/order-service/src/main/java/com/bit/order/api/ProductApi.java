package com.bit.order.api;

import com.bit.product.api.ProductInterface;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "product-service",path = "/product")
public interface ProductApi extends ProductInterface {

}
