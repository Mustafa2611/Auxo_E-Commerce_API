package com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryGroupDto {
    private Long id;
    private String nameEn;
    private String nameAr;
    private List<GetCategoryDto> subSections;
}
