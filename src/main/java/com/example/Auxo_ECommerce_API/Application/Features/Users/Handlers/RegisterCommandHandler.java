package com.example.Auxo_ECommerce_API.Application.Features.Users.Handlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.RegisterCommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Users.Role;
import com.example.Auxo_ECommerce_API.Domain.Entities.Users.User;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service("RegisterCommandHandler")
public class RegisterCommandHandler implements ICommandHandler<RegisterCommand, Result<UUID>> {
    private final IUnitOfWork unitOfWork;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Result<Result<UUID>> handle(RegisterCommand registerCommand) {
        if(!registerCommand.getPassword().equals(registerCommand.getConfirmPassword())){
            return Result.failure("Password does not match.");
        }
        Role role = unitOfWork.Roles().findByName(registerCommand.getRole());

//        User user = new User(UUID.randomUUID(),
//                registerCommand.getUserName(),
//                registerCommand.getEmail(),
//                registerCommand.getPhoneNumber(),
//                passwordEncoder.encode(registerCommand.getPassword()),
//                new ArrayList<>(List.of(
//                        role
//                    )
//                )
//        );

        User user = new User();
        user.setUsername(registerCommand.getUserName());
        user.setEmail(registerCommand.getEmail());
        user.setPhoneNumber(registerCommand.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerCommand.getPassword()));
        user.setRoles(new ArrayList<>(List.of(role)));

        unitOfWork.Users().save(user);
        Result result = unitOfWork.commit();
        if (result.isFailure()) {
            return Result.failure(result.getError());
        }
        return Result.success(user.getId());
    }
}
