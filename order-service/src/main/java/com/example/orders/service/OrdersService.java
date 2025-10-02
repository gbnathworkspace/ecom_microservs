package com.example.orders.service;

import com.example.orders.entity.Order;
import com.example.orders.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersService {
    private final OrdersRepository productRepository;

    OrdersService(OrdersRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Order> GetAllProduct()
    {
        return productRepository.findAll();
    }

    public List<Order> GetAllProduct(String userId)
    {
        return productRepository.findByUserId(userId);
    }

    public void AddProduct(Order product)
    {
        productRepository.save(product);
    }

    public void DeleteProduct(UUID productId)
    {
        productRepository.deleteById(productId);
    }

    public void UpdateProduct(Order product)
    {
    }
}
