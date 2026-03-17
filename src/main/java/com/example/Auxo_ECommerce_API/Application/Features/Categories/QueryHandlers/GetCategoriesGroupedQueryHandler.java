package com.example.Auxo_ECommerce_API.Application.Features.Categories.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.CategoryGroupDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesGroupedQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("GetCategoriesGroupedQueryHandler")
@AllArgsConstructor
public class GetCategoriesGroupedQueryHandler
        implements IQueryHandler<GetCategoriesGroupedQuery, List<CategoryGroupDto>> {

    private final IUnitOfWork unitOfWork;

    @Override
    public Result<List<CategoryGroupDto>> handle(GetCategoriesGroupedQuery request) {

        List<Category> mainSections = unitOfWork.Categories()
                .findMainSectionsWithChildren();

        List<CategoryGroupDto> grouped = mainSections.stream()
                .filter(main -> !main.getId().equals(request.getExcludeId()))
                .map(main -> {
                    List<GetCategoryDto> subs = main.getChildren().stream()
                            .filter(sub -> !sub.getId().equals(request.getExcludeId()))
                            .map(sub -> new GetCategoryDto(
                                    sub.getId(),
                                    sub.getNameEn(),
                                    sub.getNameAr(),
                                    sub.getParent() != null ? sub.getParent().getId() : null,
                                    sub.getParent() != null ? sub.getParent().getNameAr() : "—",
                                    sub.getParent() != null? sub.getParent().getNameEn() : "—",
                                    sub.getType()
                            ))
                            .collect(Collectors.toList());

                    return new CategoryGroupDto(
                            main.getId(),
                            main.getNameEn(),
                            main.getNameAr(),
                            subs
                    );
                })
                .collect(Collectors.toList());

        return Result.success(grouped);
    }
}
