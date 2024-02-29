package com.ryan.ryanshoppingmall.controller;

import com.ryan.ryanshoppingmall.dto.CreateOrderRequest;
import com.ryan.ryanshoppingmall.dto.OrderQueryParams;
import com.ryan.ryanshoppingmall.model.Order;
import com.ryan.ryanshoppingmall.service.OrderService;
import com.ryan.ryanshoppingmall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid CreateOrderRequest createOrderRequest) {
        // Create order
        Integer orderId = orderService.createOrder(userId, createOrderRequest);

        // Search for order
        Order order = orderService.getOrderById(orderId);


        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam (defaultValue = "10") @Max(1000) @Min(0) Integer limit,
                                                 @RequestParam (defaultValue = "0") @Min(0) Integer offset) {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setLimit(limit);
        orderQueryParams.setOffset(offset);

        // Get order list
        List<Order> orderList = orderService.getOrders(orderQueryParams);

        // Get the amount
        Integer count = orderService.countOrder(orderQueryParams);

        // Page
        Page<Order> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
