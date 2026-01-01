package com.example.Auxo_ECommerce_API.Repositories;

import com.example.Auxo_ECommerce_API.Models.Entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
