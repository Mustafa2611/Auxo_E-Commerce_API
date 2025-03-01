package com.example.Auxo_ECommerce_API.Repositories;

import com.example.Auxo_ECommerce_API.Models.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {}

