package com.example.Auxo_ECommerce_API.Controllers;

import com.example.Auxo_ECommerce_API.Models.Entities.Order;
import com.example.Auxo_ECommerce_API.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
