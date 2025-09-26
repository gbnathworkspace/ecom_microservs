package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<Product> GetAllProduct()
    {
        return productRepository.findAll();
    }
}
