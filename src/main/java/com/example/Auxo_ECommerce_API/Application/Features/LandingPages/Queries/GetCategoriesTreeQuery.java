package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.CategoryTreeDto;
import lombok.Data;

import java.util.List;

@Data
public class GetCategoriesTreeQuery implements IQuery<List<CategoryTreeDto>> {}
