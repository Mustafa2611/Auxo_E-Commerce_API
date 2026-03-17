package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos;


import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CategoryTreeDto {
    private Long id;
    private String nameEn;
    private String nameAr;
    private List<CategoryTreeDto> children;
}
