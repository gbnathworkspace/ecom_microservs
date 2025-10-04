package com.example.products.controller;

import com.example.products.dto.request.ProductRequest;
import com.example.products.dto.response.ProductResponse;
import com.example.products.entity.Category;
import com.example.products.entity.Product;
import com.example.products.service.CategoryService;
import com.example.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    ProductController(ProductService productService, CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProductResponse>> GetAllProducts()
    {
        List<ProductResponse> productResponses =  new ArrayList<>();
        List<Product> products = productService.GetAllProduct();
        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setName(product.getName());
            response.setPrice(product.getPrice().toString());
            response.setDescription(product.getDescription());
            productResponses.add(response);
        }

        return ResponseEntity.ok(productResponses);
    }

    @GetMapping("/get/{userid}")
    public ResponseEntity<List<ProductResponse>> GetProducts(@RequestParam(name="user")String userId)
    {
        List<ProductResponse> productResponses =  new ArrayList<>();
        List<Product> products = productService.GetAllProduct(userId);
        for (Product product : products) {
            ProductResponse response = new ProductResponse();
            response.setName(product.getName());
            response.setPrice(product.getPrice().toString());
            response.setDescription(product.getDescription());
            productResponses.add(response);
        }

        return ResponseEntity.ok(productResponses);    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> AddProduct(@RequestBody ProductRequest productRequest)
    {
        Optional<Category> category = categoryService.GetCategory(productRequest.category.getId());
        if(category.isEmpty())
        {
            categoryService.AddCategory(productRequest.category);
        }

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setCategory(productRequest.category);
        product.setUserId(productRequest.userId);
        product.setDescription(productRequest.description);
        product.setName(productRequest.name);
        product.setPrice(new BigDecimal(productRequest.price));
        product.setCreateAt(LocalDateTime.now());

        productService.AddProduct(product);
        return ResponseEntity.ok(new ProductResponse());
    }


    @PostMapping("/delete")
    public ResponseEntity<ProductResponse> DeleteProduct(@RequestParam(name = "pid")String productId)
    {
        productService.DeleteProduct(UUID.fromString(productId));
        return ResponseEntity.ok(new ProductResponse());
    }

    @PostMapping("/update")
    public ResponseEntity<ProductResponse> UpdateProduct(@RequestBody ProductRequest productRequest, String productId)
    {
        return ResponseEntity.ok(new ProductResponse());
    }
}
