package com.example.Auxo_ECommerce_API.Application.Queries.Products;

import com.example.Auxo_ECommerce_API.Application.Dtos.Products.AddProductDto;
import lombok.Data;

@Data
public class CreateNewProductCommand {
    private AddProductDto Dto;
}

