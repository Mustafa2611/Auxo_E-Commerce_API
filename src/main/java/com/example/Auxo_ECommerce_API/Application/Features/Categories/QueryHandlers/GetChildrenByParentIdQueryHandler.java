package com.example.Auxo_ECommerce_API.Application.Features.Categories.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetChildrenByParentIdQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Handler
@Service("GetChildrenByParentIdQueryHandler")
@AllArgsConstructor
public class GetChildrenByParentIdQueryHandler
        implements IQueryHandler<GetChildrenByParentIdQuery, Object> {

    private final IUnitOfWork unitOfWork;

    @Override
    public Result<Object> handle(GetChildrenByParentIdQuery request) {
        List<Category> children = unitOfWork.Categories().findByParentId(request.getParentId());

        List<GetCategoryDto> dtos = children.stream()
                .map(c -> new GetCategoryDto(
                        c.getId(), c.getNameAr(), c.getNameEn(),
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
