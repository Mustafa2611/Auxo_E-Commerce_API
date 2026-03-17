package com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.CategoryGroupDto;
import lombok.Data;

import java.util.List;
@Data
public class GetCategoriesGroupedQuery implements IQuery<List<CategoryGroupDto>> {
    private Long excludeId; // exclude current category on edit
}
