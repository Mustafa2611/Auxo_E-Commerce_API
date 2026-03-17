package com.example.Auxo_ECommerce_API.Application.Features.Products.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.CreateNewProductCommand;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Infrustracture.Services.FileStorageService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service("CreateNewProductCommandHandler")
@AllArgsConstructor
public class CreateNewProductCommandHandler implements ICommandHandler<CreateNewProductCommand, UUID> {

    private final IUnitOfWork unitOfWork;
    private final FileStorageService fileStorageService; // ← inject
    @Override
    public Result<UUID> handle(@NotNull CreateNewProductCommand request) {
        Optional<Category> category = unitOfWork.Categories().findById(request.getCategoryId());
        if(category.isEmpty())
            return Result.failure("Category not found.");

        // Handle image upload
        String imageUrl = null;
        try {
            imageUrl = fileStorageService.save(request.getImageFile());
        } catch (IOException | IllegalArgumentException e) {
            return Result.failure("Image upload failed: " + e.getMessage());
        }

        Product product = Product.builder()
                .nameAr(request.getNameAr())
                .nameEn(request.getNameEn())
                .descriptionAr(request.getDescriptionAr())
                .descriptionEn(request.getDescriptionEn())
                .InstallmentDetails(request.getInstallmentDetails())
                .price(request.getPrice())
                .stock(request.getStock())
                .imageUrl(imageUrl)
                .category(category.get())
                .build();

        // Save
        Product savedProduct = unitOfWork.Products().save(product);

        // Commit transaction
        Result commitResult = unitOfWork.commit();
        if (commitResult.isFailure()) {
            return Result.failure(commitResult.getError());
        }

        return Result.success(savedProduct.getId());
    }

}
