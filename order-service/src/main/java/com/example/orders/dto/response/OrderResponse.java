package com.example.orders.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    public String OrderId;
    public LocalDateTime createAt;
    List<OrderItemResponse> orderItem;
}

