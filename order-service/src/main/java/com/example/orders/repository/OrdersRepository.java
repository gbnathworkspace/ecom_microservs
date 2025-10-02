package com.example.orders.repository;

import com.example.orders.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(String userId);
}
