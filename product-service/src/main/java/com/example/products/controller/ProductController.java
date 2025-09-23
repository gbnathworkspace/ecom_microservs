package com.example.products.controller;

import com.example.products.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/get")
    public ResponseEntity<ProductResponse> GetAllProducts()
    {
        return ResponseEntity.ok(new ProductResponse());
    }
}
