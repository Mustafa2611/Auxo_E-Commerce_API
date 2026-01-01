package com.example.Auxo_ECommerce_API.Services;

import com.example.Auxo_ECommerce_API.Models.Entities.User;
import com.example.Auxo_ECommerce_API.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
