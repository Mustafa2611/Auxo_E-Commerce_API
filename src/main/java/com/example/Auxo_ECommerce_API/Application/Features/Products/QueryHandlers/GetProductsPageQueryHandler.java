package com.example.Auxo_ECommerce_API.Application.Features.Products.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductsPageQuery;
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
@Service("GetProductsPageQueryHandler")
public class GetProductsPageQueryHandler implements IQueryHandler<GetProductsPageQuery, List<GetProductsPageDto>> {
    private final IUnitOfWork unitOfWork;
    @Override
    public Result<List<GetProductsPageDto>> handle(GetProductsPageQuery request) {

        List<Product> products;

        if (request.getCategoryId() == null) {
            // No filter — return all products
            products = unitOfWork.Products().findAllWithCategory();

        } else {
            // Collect the category itself + all descendants
            List<Long> categoryIds = collectCategoryIds(request.getCategoryId());
            products = unitOfWork.Products().findByCategoryIds(categoryIds);
        }

        List<GetProductsPageDto> dtos = products.stream()
                .map(p -> GetProductsPageDto.builder()
                        .Id(p.getId())
                        .NameAr(p.getNameAr())
                        .NameEn(p.getNameEn())
                        .Price(p.getPrice())
                        .Stock(p.getStock())
                        .imageUrl(p.getImageUrl())
                        .CategoryTitleEn(
                                Optional.ofNullable(p.getCategory())
                                        .map(c -> c.getNameEn())
                                        .orElse("")
                        )
                        .CategoryTitleAr(
                                Optional.ofNullable(p.getCategory())
                                        .map(c -> c.getNameAr())
                                        .orElse("")
                        )
                        .build())
                .collect(Collectors.toList());

        return Result.success(dtos);
    }

    /**
     * Collects the given category ID plus all descendant IDs (children + grandchildren).
     * Works for all 3 levels: MAIN → SECTION → SUB_SECTION
     */
    private List<Long> collectCategoryIds(Long rootId) {
        List<Long> ids = new ArrayList<>();
        collectRecursive(rootId, ids);
        return ids;
    }

    private void collectRecursive(Long parentId, List<Long> ids) {
        ids.add(parentId);
        List<Category> children = unitOfWork.Categories().findByParentId(parentId);
        for (Category child : children) {
            collectRecursive(child.getId(), ids);
        }
    }
}
