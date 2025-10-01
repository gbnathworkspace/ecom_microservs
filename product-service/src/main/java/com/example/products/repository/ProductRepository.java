package com.example.products.repository;

import com.example.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByUserId(String userId);
}
