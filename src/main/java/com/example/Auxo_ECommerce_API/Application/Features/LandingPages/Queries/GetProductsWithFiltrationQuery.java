package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries;

import com.example.Auxo_ECommerce_API.Application.Common.IQuery;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.ProductCardDto;
import lombok.Data;

import java.util.List;
@Data
public class GetProductsWithFiltrationQuery implements IQuery<List<ProductCardDto>> {
    private String SearchKeyword;
    private List<Long> CategoriesIds;
    private double MinPrice;
    private double MaxPrice;
}
