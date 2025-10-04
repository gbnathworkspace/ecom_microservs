package com.example.products.controller;

import com.example.products.dto.request.CategorytRequest;
import com.example.products.dto.request.ProductRequest;
import com.example.products.dto.response.CategoryResponse;
import com.example.products.dto.response.ProductResponse;
import com.example.products.entity.Category;
import com.example.products.entity.Product;
import com.example.products.service.CategoryService;
import com.example.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CategoryResponse>> GetAllCategory()
    {
        List<CategoryResponse> categoryResponses =  new ArrayList<>();
        List<Category> categories = categoryService.GetAllCategory();
        for (Category category : categories) {
            CategoryResponse response = new CategoryResponse(category.getName(), category.getId().toString());
            categoryResponses.add(response);
        }

        return ResponseEntity.ok(categoryResponses);
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> AddCategory(@RequestBody CategorytRequest categoryRequest)
    {
        Category category = new Category();
        UUID id = UUID.randomUUID();
        category.setId(id);
        category.setName(categoryRequest.name);//rest

        Category savedCategory = categoryService.AddCategory(category);

        CategoryResponse categoryResponse = new CategoryResponse(savedCategory.getName(), savedCategory.getId().toString()){};

        return ResponseEntity.ok(categoryResponse);
    }


    @PostMapping("/delete")
    public ResponseEntity<ProductResponse> DeleteProduct(@RequestParam(name = "pid")String productId)
    {
        categoryService.DeleteProduct(UUID.fromString(productId));
        return ResponseEntity.ok(new ProductResponse());
    }

    @PostMapping("/update")
    public ResponseEntity<ProductResponse> UpdateProduct(@RequestBody ProductRequest productRequest, String productId)
    {
        return ResponseEntity.ok(new ProductResponse());
    }
}
