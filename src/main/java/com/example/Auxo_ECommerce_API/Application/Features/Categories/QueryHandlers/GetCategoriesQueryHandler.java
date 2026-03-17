package com.example.Auxo_ECommerce_API.Application.Features.Categories.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesQuery;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("GetCategoriesQueryHandler")
public class GetCategoriesQueryHandler implements IQueryHandler<GetCategoriesQuery, List<GetCategoryDto>> {
    private IUnitOfWork unitOfWork;
    @Override
    public Result<List<GetCategoryDto>> handle(GetCategoriesQuery getCategoriesQuery) {
        List<Category> categories = unitOfWork.Categories().findAllMainSections();
        if(categories.isEmpty())
            return Result.failure("there is no categories");
        List<GetCategoryDto> categoryDtos = categories.stream()
                .map(c -> new GetCategoryDto(
                        c.getId(),
                        c.getNameEn(),
                        c.getNameAr(),
                        c.getParent() != null ? c.getParent().getId() : null,
                        c.getParent() != null ? c.getParent().getNameAr() : null,
                        c.getParent() != null ? c.getParent().getNameEn() : null,
                        c.getType()
                ))
                .collect(Collectors.toList());
        return Result.success(categoryDtos);
    }

}

