package com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesQuery implements IQuery<List<GetCategoryDto>> {

}
