package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dto.CreateOrderRequest;

public interface OrderService {
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
