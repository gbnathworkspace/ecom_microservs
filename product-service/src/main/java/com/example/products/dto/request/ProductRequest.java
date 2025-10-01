package com.example.products.dto.request;

import com.example.products.entity.Category;

public class ProductRequest {
    public String name;
    public String price;
    public String description;
    public Category category; // Changed from Category to String
    public String userId;
}