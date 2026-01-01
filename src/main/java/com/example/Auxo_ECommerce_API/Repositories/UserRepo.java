package com.example.Auxo_ECommerce_API.Repositories;

import com.example.Auxo_ECommerce_API.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
