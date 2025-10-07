package com.example.orders.controller;

import com.example.orders.dto.response.OrderResponse;
import com.example.orders.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService orderService;

    OrdersController(OrdersService categoryService)
    {
        this.orderService = categoryService;
    }

    @GetMapping("/get/{order-id}")
    public ResponseEntity<OrderResponse> GetOrder(@RequestParam String orderId)
    {

    }

}
