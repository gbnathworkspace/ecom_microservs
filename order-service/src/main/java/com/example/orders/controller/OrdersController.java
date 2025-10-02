package com.example.orders.controller;

import com.example.orders.dto.request.OrderRequest;
import com.example.orders.dto.response.OrderResponse;
import com.example.orders.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class OrdersController {
    private final OrdersService ordersService;

    OrdersController(OrdersService categoryService)
    {
        this.ordersService = categoryService;
    }

}
