package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dto.CreateOrderRequest;
import com.ryan.ryanshoppingmall.model.Order;

public interface OrderService {
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    public Order getOrderById(Integer orderId);
}
