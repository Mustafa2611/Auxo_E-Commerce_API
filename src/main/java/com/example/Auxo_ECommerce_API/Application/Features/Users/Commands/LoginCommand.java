package com.example.Auxo_ECommerce_API.Application.Features.Users.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos.LoginResponse;
import lombok.Data;

@Data
public class LoginCommand implements ICommand<LoginResponse> {
    private String email;
    private String password;
}
