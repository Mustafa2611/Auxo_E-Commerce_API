package com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos;

import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class GetProductDetailsDto{
    private UUID id;
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
    private String InstallmentDetails;
    private double price;
    private int stock;
    private String imageUrl;
    private Long CategoryId;
    private String CategoryTitleAr;
    private String CategoryTitleEn;
    private String CategoryType;

//
//    public GetProductDetailsDto toDto(@NotNull Product entity)
//    {
//        return new GetProductDetailsDto(
//                entity.getId(),
//                entity.getNameAr(),
//                entity.getNameEn(),
//                entity.getDescriptionAr(),
//                entity.getDescriptionEn(),
//                entity.getInstallmentDetails(),
//                entity.getPrice(),
//                entity.getStock(),
//                entity.getCategory() == null? 0 : entity.getCategory().getId(),
//                entity.getCategory() == null? "": entity.getCategory().getNameAr(),
//                entity.getCategory() == null? "": entity.getCategory().getNameEn()
//        );
//    }
}
