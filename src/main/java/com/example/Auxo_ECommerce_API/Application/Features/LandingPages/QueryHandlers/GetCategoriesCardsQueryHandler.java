package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.CategoryCardDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.GetCategoriesCardsQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("GetCategoriesCardsQueryHandler")
public class GetCategoriesCardsQueryHandler implements IQueryHandler<GetCategoriesCardsQuery, List<CategoryCardDto>> {
    private final IUnitOfWork unitOfWork;

    @Override
    public Result<List<CategoryCardDto>> handle(GetCategoriesCardsQuery query) {
        List<Category> mainCategories = unitOfWork.Categories().findByType(CategoryType.MAIN, null);
        if (mainCategories.isEmpty())
            return Result.failure("No categories found");

        List<CategoryCardDto> dtos = mainCategories.stream()
                .map(c -> CategoryCardDto.builder()
                        .Id(c.getId())
                        .NameEn(c.getNameEn())
                        .NameAr(c.getNameAr())
                        .ProductsCount(countProductsRecursively(c.getId()))
                        .build())
                .collect(Collectors.toList());

        return Result.success(dtos);
    }

    private int countProductsRecursively(Long categoryId) {
        // Count products directly in this category
        int count = unitOfWork.Products().countByCategoryId(categoryId);
        // Add counts from all children
        List<Category> children = unitOfWork.Categories().findByParentId(categoryId);
        for (Category child : children) {
            count += countProductsRecursively(child.getId());
        }
        return count;
    }
}
