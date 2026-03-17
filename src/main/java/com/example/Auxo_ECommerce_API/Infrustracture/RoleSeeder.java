package com.example.Auxo_ECommerce_API.Infrustracture;

import com.example.Auxo_ECommerce_API.Domain.Common.Roles;
import com.example.Auxo_ECommerce_API.Domain.Entities.Users.Role;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {
    private final IUnitOfWork unitOfWork;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        SeedRole(Roles.Admin);
        SeedRole(Roles.User);
        unitOfWork.commit();
    }
    private void SeedRole(String name){
        if (!unitOfWork.Roles().existsByName(name)) {
//            unitOfWork.Roles().save(new Role(UUID.randomUUID(),
//                    name,
//                    new ArrayList<>()));

            Role role = new Role();
            role.setName(name);
            unitOfWork.Roles().save(role);
        }
    }
}
