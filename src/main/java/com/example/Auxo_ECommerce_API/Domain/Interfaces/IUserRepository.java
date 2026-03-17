package com.example.Auxo_ECommerce_API.Domain.Interfaces;

import com.example.Auxo_ECommerce_API.Domain.Entities.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
