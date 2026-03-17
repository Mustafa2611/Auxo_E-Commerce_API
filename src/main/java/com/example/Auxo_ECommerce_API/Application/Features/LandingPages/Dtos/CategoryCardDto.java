package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryCardDto {
    private Long Id;
    private String NameAr;
    private String NameEn;
    private int ProductsCount;

}
