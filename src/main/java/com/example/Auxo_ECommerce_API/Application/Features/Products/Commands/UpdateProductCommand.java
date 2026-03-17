package com.example.Auxo_ECommerce_API.Application.Features.Products.Commands;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class UpdateProductCommand implements ICommand<UUID> {
    private UUID Id;
    private String nameAr;
    private String nameEn;
    private String descriptionAr;
    private String descriptionEn;
    private String InstallmentDetails;
    private double price;
    private int stock;
    private Long CategoryId;
    private String CategoryType; // ← add this
    @JsonIgnore
    private MultipartFile imageFile;     // ← new image to upload (optional)
    private String existingImageUrl;     // ← current saved image url (keep if no new upload)

    public Product toEntity(Product product, Category category, UpdateProductCommand request){
        product.setNameAr(request.getNameAr());
        product.setNameEn(request.getNameEn());
        product.setDescriptionAr(request.getDescriptionAr());
        product.setDescriptionEn(request.getDescriptionEn());
        product.setInstallmentDetails(request.getInstallmentDetails());
        product.setStock(request.getStock());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        return product;
    }
}
