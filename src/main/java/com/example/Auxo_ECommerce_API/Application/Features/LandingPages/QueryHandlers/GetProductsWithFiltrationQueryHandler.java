package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.ProductCardDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.GetProductsWithFiltrationQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("GetProductsWithFiltrationQueryHandler")
public class GetProductsWithFiltrationQueryHandler implements IQueryHandler<GetProductsWithFiltrationQuery,List<ProductCardDto>> {
    private final IUnitOfWork unitOfWork;
    @Override
    public Result<List<ProductCardDto>> handle(GetProductsWithFiltrationQuery request) {
        // Collect all relevant category IDs (including children/grandchildren)
        List<Long> allCategoryIds = new ArrayList<>();
        if (request.getCategoriesIds() != null && !request.getCategoriesIds().isEmpty()) {
            for (Long catId : request.getCategoriesIds()) {
                collectCategoryIds(catId, allCategoryIds);
            }
        }

        List<Product> products = allCategoryIds.isEmpty()
                ? unitOfWork.Products().findAllWithCategory()
                : unitOfWork.Products().findByCategoryIds(allCategoryIds);

        // Apply search + price filters in memory
        List<ProductCardDto> dtos = products.stream()
                .filter(p -> {
                    if (request.getSearchKeyword() != null && !request.getSearchKeyword().isBlank()) {
                        String kw = request.getSearchKeyword().toLowerCase();
                        return (p.getNameEn() != null && p.getNameEn().toLowerCase().contains(kw))
                                || (p.getNameAr() != null && p.getNameAr().toLowerCase().contains(kw));
                    }
                    return true;
                })
                .filter(p -> p.getPrice() >= request.getMinPrice()
                        && (request.getMaxPrice() <= 0 || p.getPrice() <= request.getMaxPrice()))
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

    private void collectCategoryIds(Long categoryId, List<Long> ids) {
        ids.add(categoryId);
        List<Category> children = unitOfWork.Categories().findByParentId(categoryId);
        for (Category child : children) {
            collectCategoryIds(child.getId(), ids);
        }
    }
}
