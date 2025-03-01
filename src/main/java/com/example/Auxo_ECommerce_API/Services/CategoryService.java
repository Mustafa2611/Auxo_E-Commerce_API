package com.example.Auxo_ECommerce_API.Services;

import com.example.Auxo_ECommerce_API.Models.Entities.Category;
import com.example.Auxo_ECommerce_API.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepository;
    public List<Category> getAllCategories() { return categoryRepository.findAll(); }
    public Category saveCategory(Category category) { return categoryRepository.save(category); }
}

