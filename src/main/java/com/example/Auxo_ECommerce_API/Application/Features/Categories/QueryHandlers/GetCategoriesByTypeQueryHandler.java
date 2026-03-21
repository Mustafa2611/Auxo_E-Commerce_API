package com.example.Auxo_ECommerce_API.Application.Features.Categories.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesByTypeQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("GetCategoriesByTypeQueryHandler")
@AllArgsConstructor
public class GetCategoriesByTypeQueryHandler
        implements IQueryHandler<GetCategoriesByTypeQuery, List<GetCategoryDto>> {

    private final IUnitOfWork unitOfWork;

    @Override
    public Result<List<GetCategoryDto>> handle(GetCategoriesByTypeQuery request) {
        List<Category> categories = unitOfWork.Categories()
                .findByType(request.getType(), request.getExcludeId());

        if (categories.isEmpty())
            return Result.success(List.of());

        List<GetCategoryDto> dtos = categories.stream()
                .map(c -> new GetCategoryDto(
                        c.getId(),
                        c.getNameAr(),
                        c.getNameEn(),
                        c.getParent() != null ? c.getParent().getId() : null,
                        c.getParent() != null ? c.getParent().getNameAr() : null,
                        c.getParent() != null ? c.getParent().getNameEn() : null,
                        c.getType(),
                        c.getImageUrl()
                ))
                .collect(Collectors.toList());

        return Result.success(dtos);
    }
}
