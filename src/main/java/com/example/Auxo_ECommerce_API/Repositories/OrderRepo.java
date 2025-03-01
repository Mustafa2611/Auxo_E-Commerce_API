package com.example.Auxo_ECommerce_API.Repositories;

import com.example.Auxo_ECommerce_API.Models.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
