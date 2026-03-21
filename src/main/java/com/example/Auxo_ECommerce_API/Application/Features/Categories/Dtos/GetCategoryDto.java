package com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos;

import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryDto {
    private Long Id;
    private String NameAr;
    private String NameEn;
    private Long parentId;// ← needed for pre-selection in edit form
    private String ParentNameAr;
    private String ParentNameEn;
    private CategoryType Type;
    private String imageUrl;

}
