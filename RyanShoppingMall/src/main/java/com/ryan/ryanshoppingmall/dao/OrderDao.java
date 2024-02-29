package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    public Integer createOrder(Integer userId, int totalAmount);

    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
