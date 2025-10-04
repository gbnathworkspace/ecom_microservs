package com.example.orders.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table
public class Order {
    public UUID orderId;
    public BigDecimal totalPrice;
    public LocalDateTime createdAt;
    public LocalDateTime updateAt;
    public orderStatus status;
    public String shippingAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}


