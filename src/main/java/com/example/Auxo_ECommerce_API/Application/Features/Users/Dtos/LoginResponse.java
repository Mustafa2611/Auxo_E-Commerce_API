package com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@Data
@AllArgsConstructor
public class LoginResponse {
    private String id ;
    private String userName;
    private String email;
    private String phoneNumber;

}
