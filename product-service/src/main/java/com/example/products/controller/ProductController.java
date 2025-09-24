package com.example.products.controller;

import com.example.products.dto.request.ProductRequest;
import com.example.products.dto.response.ProductResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponse>> GetAllProducts()
    {
        return ResponseEntity.ok(new ArrayList<ProductResponse>(){});
    }

    @GetMapping("/get")
    public ResponseEntity<ProductResponse> GetProducts(@RequestParam(name="user")String userId)
    {
        return ResponseEntity.ok(new ProductResponse());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> AddProduct(@RequestBody ProductRequest productRequest)
    {
        return ResponseEntity.ok(new ProductResponse());
    }

    @PostMapping("/delete")
    public ResponseEntity<ProductResponse> DeleteProduct(@RequestParam(name = "pid")String productId)
    {
        return ResponseEntity.ok(new ProductResponse());
    }

    @PostMapping("/update")
    public ResponseEntity<ProductResponse> UpdateProduct(@RequestBody ProductRequest productRequest, String productId)
    {
        return ResponseEntity.ok(new ProductResponse());
    }
}
