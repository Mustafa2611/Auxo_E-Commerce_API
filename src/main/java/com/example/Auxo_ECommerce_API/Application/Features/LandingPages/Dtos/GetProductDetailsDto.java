package com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Dtos;

import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GetProductDetailsDto {
    private String id;
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
    private String InstallmentDetails;
    private double price;
    private int stock;
    private String imageUrl;
    private Long categoryId;
    private String categoryTitleAr;
    private String categoryTitleEn;

    private int ViewedCount;
}
