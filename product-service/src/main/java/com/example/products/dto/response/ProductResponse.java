package com.example.products.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductResponse {
    public String name;
    public String price;
    public LocalDateTime createAt;
    public String description;
    private String category;
}