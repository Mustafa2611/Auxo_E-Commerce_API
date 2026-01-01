package com.example.Auxo_ECommerce_API.Application.Queries.Categories;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Dtos.Cateories.GetCategoryDto;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesQuery implements IQuery<List<GetCategoryDto>> {

}
