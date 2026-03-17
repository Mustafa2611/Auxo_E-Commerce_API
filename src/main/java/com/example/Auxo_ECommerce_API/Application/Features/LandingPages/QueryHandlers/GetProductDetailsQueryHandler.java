package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos.GetProductDetailsDto;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.GetProductDetailsQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service("GetProductDetailsQueryHandler")
public class GetProductDetailsQueryHandler implements IQueryHandler<GetProductDetailsQuery, GetProductDetailsDto> {
    private final IUnitOfWork unitOfWork;
    @Override
    public Result<GetProductDetailsDto> handle(GetProductDetailsQuery request) {
        Optional<Product> productOpt = unitOfWork.Products().findByIdWithCategory(request.getId());
        if (productOpt.isEmpty())
            return Result.failure("Product not found");

        Product product = productOpt.get();

        // Increment view count
        product.setViewedCount(product.getViewedCount() + 1);
        unitOfWork.Products().save(product);
        unitOfWork.commit();

        GetProductDetailsDto dto = GetProductDetailsDto.builder()
                .id(product.getId())
                .nameAr(product.getNameAr())
                .nameEn(product.getNameEn())
                .stock(product.getStock())
                .price(product.getPrice())
                .descriptionAr(product.getDescriptionAr())
                .descriptionEn(product.getDescriptionEn())
                .InstallmentDetails(product.getInstallmentDetails())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategory() == null ? null : product.getCategory().getId())
                .categoryTitleAr(product.getCategory() == null ? "" : product.getCategory().getNameAr())
                .categoryTitleEn(product.getCategory() == null ? "" : product.getCategory().getNameEn())
                .ViewedCount(product.getViewedCount())
                .build();

        return Result.success(dto);
    }
}
