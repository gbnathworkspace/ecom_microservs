package com.example.products.service;

import com.example.products.entity.Product;
import com.example.products.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

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

    public List<Product> GetAllProduct(String userId)
    {
        return productRepository.findByUserId(userId);
    }

    public void AddProduct(Product product)
    {
        productRepository.save(product);
    }

    public void DeleteProduct(UUID productId)
    {
        productRepository.deleteById(productId);
    }

    public void UpdateProduct(Product product)
    {
    }
}
