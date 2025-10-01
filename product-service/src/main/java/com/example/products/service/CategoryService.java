package com.example.products.service;

import com.example.products.entity.Category;
import com.example.products.entity.Product;
import com.example.products.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> GetAllCategory()
    {
        return categoryRepository.findAll();
    }

    public void AddCategory(Category category)
    {
        categoryRepository.save(category);
    }

    public void DeleteProduct(UUID categoryId)
    {
        categoryRepository.deleteById(categoryId);
    }

    public void UpdateProduct(Category category)
    {
    }
}
