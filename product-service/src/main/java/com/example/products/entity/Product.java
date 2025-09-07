package com.example.products.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    UUID Id;
    String Name;
    BigDecimal price;
    LocalDateTime createAt;
    String categoryId;
    String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
