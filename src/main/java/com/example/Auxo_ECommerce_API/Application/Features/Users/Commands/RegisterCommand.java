package com.example.Auxo_ECommerce_API.Application.Features.Users.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;
@Data
public class RegisterCommand implements ICommand<String> {
    @JsonProperty("Email")
    private String Email;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("PhoneNumber")
    private String PhoneNumber;

    @JsonProperty("ImageUrl")
    private String ImageUrl;

    @JsonProperty("Role")
    private String Role;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("confirmPassword")
    private String confirmPassword;
}
