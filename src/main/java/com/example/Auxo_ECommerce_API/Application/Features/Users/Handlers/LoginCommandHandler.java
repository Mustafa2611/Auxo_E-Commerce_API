package com.example.Auxo_ECommerce_API.Application.Features.Users.Handlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.LoginCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos.LoginResponse;
import com.example.Auxo_ECommerce_API.Domain.Entities.Users.User;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("LoginCommandHandler")
@AllArgsConstructor
public class LoginCommandHandler implements ICommandHandler<LoginCommand, Result<LoginResponse>> {
    private final IUnitOfWork unitOfWork;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Result<Result<LoginResponse>> handle(LoginCommand loginCommand) {
        User user = unitOfWork.Users().findByEmail(loginCommand.getEmail());
        if(user == null)
            return Result.failure("User not found");
        if(!passwordEncoder.matches(loginCommand.getPassword(),user.getPassword()))
            return Result.failure("incorrect password");
        LoginResponse response= new LoginResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber());
        return Result.success(response);
    }
}
