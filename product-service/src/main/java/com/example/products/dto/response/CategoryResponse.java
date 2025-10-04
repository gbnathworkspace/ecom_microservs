package com.example.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class CategoryResponse {
    private String name;
    private String id;//comment
}