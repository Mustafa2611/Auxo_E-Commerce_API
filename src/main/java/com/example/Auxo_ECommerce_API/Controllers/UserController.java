package com.example.Auxo_ECommerce_API.Controllers;

import com.example.Auxo_ECommerce_API.Models.Entities.User;
import com.example.Auxo_ECommerce_API.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
