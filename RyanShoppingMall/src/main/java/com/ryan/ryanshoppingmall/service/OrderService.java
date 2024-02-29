package com.ryan.ryanshoppingmall.service;

import com.ryan.ryanshoppingmall.dto.CreateOrderRequest;
import com.ryan.ryanshoppingmall.dto.OrderQueryParams;
import com.ryan.ryanshoppingmall.model.Order;

import java.util.List;

public interface OrderService {
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    public Order getOrderById(Integer orderId);

    public List<Order> getOrders(OrderQueryParams orderQueryParams);

    public Integer countOrder(OrderQueryParams orderQueryParams);
}
