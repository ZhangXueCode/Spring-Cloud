package com.bit.order.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderInfo {
    private Integer id;
    private Integer userId;
    private Integer  num;
    private Integer price;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
