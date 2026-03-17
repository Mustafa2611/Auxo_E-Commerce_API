package com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos;

import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GetCategoryDetailsDto {
    private Long id;
    private String nameEn;
    private String nameAr;
    private CategoryType type;
    private Long parentId;
    private String parentNameEn;
    private String parentNameAr;
    private List<GetCategoryDto> subCategories;
}
