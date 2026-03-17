package com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesByTypeQuery implements IQuery<List<GetCategoryDto>> {
    private CategoryType type;
    private Long excludeId;
}