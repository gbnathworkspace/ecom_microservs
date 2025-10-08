package com.example.orders.controller;

import com.example.orders.dto.response.OrderItemResponse;
import com.example.orders.dto.response.OrderResponse;
import com.example.orders.entity.Order;
import com.example.orders.entity.OrderItem;
import com.example.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;

    OrdersController(OrderService categoryService)
    {
        this.orderService = categoryService;
    }

    @GetMapping("/get/{order-id}")
    public ResponseEntity<OrderResponse> GetOrder(@RequestParam String orderId)
    {
        OrderResponse orderResponse = new OrderResponse();
        List<Order> orders = orderService.GetOrder(orderId);
        for(Order order:orders)
        {
            orderResponse.setOrderId(order.getOrderId().toString());
            orderResponse.setCreateAt(order.getCreatedAt());
            List<OrderItem> orderItems = order.getItems();
            var orderItem1 = orderResponse.getOrderItem();
            for (OrderItem orderItem:orderItems)
            {
                OrderItemResponse orderItemResponse = new OrderItemResponse();
                orderItemResponse.productName = orderItem.productName;
                orderItemResponse.productPrice = orderItem.productPrice.toString();
                orderItem1.add(orderItemResponse);
            }
            orderResponse.setOrderItem(orderItem1);
        }
        return ResponseEntity.ok(orderResponse);
    }
}
