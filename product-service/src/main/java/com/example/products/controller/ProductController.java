package com.example.products.controller;

import com.example.products.dto.request.ProductRequest;
import com.example.products.dto.response.ProductResponse;
import com.example.products.entity.Product;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;

    ProductController(ProductRepository productRepository, ProductService productService)
    {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponse>> GetAllProducts()
    {
        List<ProductResponse> productResponses =  new ArrayList<>();
        List<Product> products = productService.GetAllProduct();
        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setName(product.getName()); // assuming getName() exists
            response.setPrice(product.getPrice().toString()); // add other fields as needed
            response.setDescription(product.getDescription()); // example
            productResponses.add(response);
        }

        return ResponseEntity.ok(productResponses);
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
