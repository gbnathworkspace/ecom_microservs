package com.example.products.dto.request;

import com.example.products.entity.Category;

import java.time.LocalDateTime;

public class ProductRequest {
    public String name;
    public String price;
    public String description;
    public Category category;
    public String userId;
}