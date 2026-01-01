package com.example.Auxo_ECommerce_API.Application.Dtos.Products;

import lombok.Builder;
import lombok.Data;

@Data
public class AddProductDto {
    private String name;
    private String description;
    private double price;
    private int stock;
    private long CategoryId;
}
