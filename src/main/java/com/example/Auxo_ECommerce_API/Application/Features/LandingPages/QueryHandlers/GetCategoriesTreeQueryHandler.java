package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.CategoryTreeDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.GetCategoriesTreeQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("GetCategoriesTreeQueryHandler")
@AllArgsConstructor
public class GetCategoriesTreeQueryHandler
        implements IQueryHandler<GetCategoriesTreeQuery, List<CategoryTreeDto>> {
    private final IUnitOfWork unitOfWork;

    @Override
    public Result<List<CategoryTreeDto>> handle(GetCategoriesTreeQuery query) {
        List<Category> mains = unitOfWork.Categories().findByType(CategoryType.MAIN, null);
        List<CategoryTreeDto> tree = mains.stream()
                .map(m -> buildNode(m))
                .collect(Collectors.toList());
        return Result.success(tree);
    }

    private CategoryTreeDto buildNode(Category cat) {
        List<Category> children = unitOfWork.Categories().findByParentId(cat.getId());
        return CategoryTreeDto.builder()
                .id(cat.getId())
                .nameEn(cat.getNameEn())
                .nameAr(cat.getNameAr())
                .children(children.stream().map(this::buildNode).collect(Collectors.toList()))
                .build();
    }
}
