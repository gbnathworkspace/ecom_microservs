package com.example.orders.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
public class OrderItem
{
    @Id
    public UUID Id;
    public UUID productId;
    public String productName;
    public BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
