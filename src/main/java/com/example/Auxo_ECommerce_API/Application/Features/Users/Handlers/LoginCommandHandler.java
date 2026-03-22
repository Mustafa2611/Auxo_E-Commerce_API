package com.example.Auxo_ECommerce_API.Application.Features.Users.Handlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.LoginCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos.LoginResponse;
import com.example.Auxo_ECommerce_API.Domain.Entities.Users.User;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Infrustracture.Services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("LoginCommandHandler")
@AllArgsConstructor
public class LoginCommandHandler implements ICommandHandler<LoginCommand, Result<LoginResponse>> {
    private final IUnitOfWork unitOfWork;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Override
    @Transactional          // ← keeps the JPA session open so lazy collections can be loaded
    public Result<Result<LoginResponse>> handle(LoginCommand loginCommand) {
        User user = unitOfWork.Users().findByEmail(loginCommand.getEmail());
        if(user == null)
            return Result.failure("User not found");
        if(!passwordEncoder.matches(loginCommand.getPassword(),user.getPassword()))
            return Result.failure("incorrect password");

        // user.getRoles() is a lazy collection — safe to access inside @Transactional
        List<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        LoginResponse response= new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber(),
                roleNames,
                null);

        // Generate JWT and attach it to the response
        String token = jwtService.generateToken(response);
        response.setToken(token);

        return Result.success(response);
    }
}
