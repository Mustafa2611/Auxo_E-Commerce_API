package com.example.Auxo_ECommerce_API.Application.Features.Users.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import lombok.Data;

import java.util.UUID;
@Data
public class RegisterCommand implements ICommand<UUID> {
    private String Email ;
    private String UserName;
    private String PhoneNumber;
    private String ImageUrl;
    private String Role;
    private String Password;
    private String confirmPassword;
}
