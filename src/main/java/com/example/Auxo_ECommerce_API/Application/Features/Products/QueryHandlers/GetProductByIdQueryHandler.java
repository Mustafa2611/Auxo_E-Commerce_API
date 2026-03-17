package com.example.Auxo_ECommerce_API.Application.Features.Products.QueryHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductDetailsDto;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductByIdQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service("GetProductByIdQueryHandler")
public class GetProductByIdQueryHandler implements IQueryHandler<GetProductByIdQuery, GetProductDetailsDto> {
    private final IUnitOfWork _unitOfWork;
    @Override
    public Result<GetProductDetailsDto> handle(GetProductByIdQuery request) {
        Optional<Product> product = _unitOfWork.Products().findByIdWithCategory(request.getId());
        if(product.isEmpty())
            return Result.failure("product not found");

        GetProductDetailsDto dto = GetProductDetailsDto.builder()
                .id(product.get().getId())
                .nameAr(product.get().getNameAr())
                .nameEn(product.get().getNameEn())
                .stock(product.get().getStock())
                .price(product.get().getPrice())
                .descriptionAr(product.get().getDescriptionAr())
                .descriptionEn(product.get().getDescriptionEn())
                .InstallmentDetails(product.get().getInstallmentDetails())
                .imageUrl(product.get().getImageUrl())
                .CategoryId(product.get().getCategory() == null ? null : product.get().getCategory().getId())
                .CategoryTitleAr(product.get().getCategory() == null? "": product.get().getCategory().getNameAr())
                .CategoryTitleEn(product.get().getCategory() == null? "":product.get().getCategory().getNameEn())
                .CategoryType(product.get().getCategory() != null ? product.get().getCategory().getType().name() : null)
                .build();
        return Result.success(dto);
    }
}
