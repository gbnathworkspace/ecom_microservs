package com.example.orders.service;

import com.example.orders.entity.Order;
import com.example.orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrdersRepository ordersRepository;

    OrderService(OrdersRepository ordersRepository)
    {
        this.ordersRepository = ordersRepository;
    }

    public List<Order> GetAllOrder(String userId)
    {
        return ordersRepository.findByUserId(userId);
    }

    public List<Order> GetOrder(String orderId)
    {
        return ordersRepository.findAllById(Collections.singleton(UUID.fromString(orderId)));
    }

    public void AddOrder(Order order)
    {
        ordersRepository.save(order);
    }

    public void DeleteOrder(UUID productId)
    {
        ordersRepository.deleteById(productId);
    }

    public void UpdateOrder(Order product)
    {
    }
}
