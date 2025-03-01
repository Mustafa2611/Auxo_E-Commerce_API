package com.example.Auxo_ECommerce_API.Services;

import com.example.Auxo_ECommerce_API.Models.Entities.Product;
import com.example.Auxo_ECommerce_API.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}