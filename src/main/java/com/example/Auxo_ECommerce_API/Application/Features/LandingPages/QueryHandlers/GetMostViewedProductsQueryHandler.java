package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.ProductCardDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.GetMostViewedProductsQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("GetMostViewedProductsQueryHandler")
public class GetMostViewedProductsQueryHandler implements IQueryHandler<GetMostViewedProductsQuery, List<ProductCardDto>> {
    private final IUnitOfWork unitOfWork;
    @Override
    public Result<List<ProductCardDto>> handle(GetMostViewedProductsQuery query) {
        List<Product> products = unitOfWork.Products().findTopByViewedCount(8);

        List<ProductCardDto> dtos = products.stream()
                .map(p -> ProductCardDto.builder()
                        .Id(p.getId())
                        .NameAr(p.getNameAr())
                        .NameEn(p.getNameEn())
                        .Price(p.getPrice())
                        .ImageUrl(p.getImageUrl())
                        .CategoryTitleEn(p.getCategory() != null ? p.getCategory().getNameEn() : "")
                        .CategoryTitleAr(p.getCategory() != null ? p.getCategory().getNameAr() : "")
                        .build())
                .collect(Collectors.toList());

        return Result.success(dtos);
    }
}
