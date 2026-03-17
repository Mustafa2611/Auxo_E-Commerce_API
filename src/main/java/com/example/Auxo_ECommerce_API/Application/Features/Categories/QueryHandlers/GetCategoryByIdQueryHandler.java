package com.example.Auxo_ECommerce_API.Application.Features.Categories.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDetailsDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoryByIdQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("GetCategoryByIdQueryHandler")
public class GetCategoryByIdQueryHandler implements IQueryHandler<GetCategoryByIdQuery, GetCategoryDto> {
    private final IUnitOfWork _unitOfWork;
    @Override
    public Result<GetCategoryDto> handle(GetCategoryByIdQuery request) {
        Optional<Category> optional = _unitOfWork.Categories().findById(request.getId());
        if(optional.isEmpty())
            return Result.failure("category not found");
        Category category = optional.get();
        // Fetch sub categories
        List<GetCategoryDto> subCategories = _unitOfWork.Categories()
                .findByParentId(request.getId())
                .stream()
                .map(sub -> new GetCategoryDto(
                        sub.getId(),
                        sub.getNameAr(),
                        sub.getNameEn(),
                        sub.getParent() != null ? sub.getParent().getId() : null,
                        sub.getParent() != null ? sub.getParent().getNameAr() : null,
                        sub.getParent() != null ? sub.getParent().getNameEn() : null,
                        sub.getType()
                ))
                .collect(Collectors.toList());
        GetCategoryDetailsDto dto = new GetCategoryDetailsDto(
                category.getId(),
                category.getNameEn(),
                category.getNameAr(),
                category.getType(),
                category.getParent() != null ? category.getParent().getId() : null,
                category.getParent() != null ? category.getParent().getNameEn() : null,
                category.getParent() != null ? category.getParent().getNameAr() : null,
                subCategories
        );

        return Result.success(dto);
    }
}
