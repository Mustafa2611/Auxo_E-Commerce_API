package com.example.Auxo_ECommerce_API.Services;

import com.example.Auxo_ECommerce_API.Models.Entities.Order;
import com.example.Auxo_ECommerce_API.Repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
