package com.example.Auxo_ECommerce_API.Application.Features.Products.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class CreateNewProductCommand implements ICommand<UUID> {
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
    private String InstallmentDetails;
    private double price;
    private int stock;
    private long CategoryId;
    @JsonIgnore
    private MultipartFile imageFile; // ← add this (org.springframework.web.multipart)
}

