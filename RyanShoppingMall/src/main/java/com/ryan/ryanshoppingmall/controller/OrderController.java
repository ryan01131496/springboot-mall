package com.ryan.ryanshoppingmall.controller;

import com.ryan.ryanshoppingmall.dto.CreateOrderRequest;
import com.ryan.ryanshoppingmall.model.Order;
import com.ryan.ryanshoppingmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
