package com.ryan.ryanshoppingmall.dao;

import com.ryan.ryanshoppingmall.dto.OrderQueryParams;
import com.ryan.ryanshoppingmall.model.Order;
import com.ryan.ryanshoppingmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    public Integer createOrder(Integer userId, int totalAmount);

    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    public Order getOrderById(Integer orderId);

    public List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
