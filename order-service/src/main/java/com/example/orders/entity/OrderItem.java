package com.example.orders.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
public class OrderItem
{
    public UUID Id;
    public UUID productId;
    public String productName;
    public BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;
}
