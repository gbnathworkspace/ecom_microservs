package com.example.orders.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse
{
    public String productName;
    public String productPrice;
    public String productQuantity;
}
