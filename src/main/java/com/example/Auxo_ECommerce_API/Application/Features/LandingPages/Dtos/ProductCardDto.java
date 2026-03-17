package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductCardDto {
    private String Id;
    private String NameAr;
    private String NameEn;
    private String ImageUrl;
    private double Price;
    private String CategoryTitleAr;
    private String CategoryTitleEn;

}
