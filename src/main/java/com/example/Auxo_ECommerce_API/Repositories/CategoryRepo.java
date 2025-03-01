package com.example.Auxo_ECommerce_API.Repositories;

import com.example.Auxo_ECommerce_API.Models.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
