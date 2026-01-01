package com.example.Auxo_ECommerce_API.Application.Queries.Categories.Handler;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Dtos.Cateories.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Queries.Categories.GetCategoriesQuery;
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
        List<Category> categories = unitOfWork.Categories().findAll();
        if(categories.isEmpty())
            return Result.failure("there is no categories");
        List<GetCategoryDto> categoryDtos = categories.stream()
                .map(c -> new GetCategoryDto(
                        c.getId(),
                        c.getNameEn(),
                        c.getNameAr()
                ))
                .collect(Collectors.toList());
        return Result.success(categoryDtos);
    }

}

