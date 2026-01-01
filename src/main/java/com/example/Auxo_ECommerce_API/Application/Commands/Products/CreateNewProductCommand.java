package com.example.Auxo_ECommerce_API.Application.Commands.Products;

import com.example.Auxo_ECommerce_API.Application.Dtos.Products.AddProductDto;
import lombok.*;

@Data
public class CreateNewProductCommand {
    private AddProductDto Dto;
}

