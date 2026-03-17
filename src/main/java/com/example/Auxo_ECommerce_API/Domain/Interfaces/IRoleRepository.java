package com.example.Auxo_ECommerce_API.Domain.Interfaces;

import com.example.Auxo_ECommerce_API.Domain.Entities.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByName(String name);
    Role findByName(String name);
}
