package com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos;

import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetProductsPageDto {
    private String Id;
    private String NameAr;
    private String NameEn;
    private double Price;
    private int Stock;
    private String imageUrl;
    private String CategoryTitleAr;
    private String CategoryTitleEn;


    public GetProductsPageDto toDto(@NotNull Product entity)
    {
        return new GetProductsPageDto(
                entity.getId(),
                entity.getNameAr(),
                entity.getNameEn(),
                entity.getPrice(),
                entity.getStock(),
                entity.getImageUrl(),
                entity.getCategory() == null? "": entity.getCategory().getNameAr(),
                entity.getCategory() == null? "": entity.getCategory().getNameEn()
        );
    }
}
