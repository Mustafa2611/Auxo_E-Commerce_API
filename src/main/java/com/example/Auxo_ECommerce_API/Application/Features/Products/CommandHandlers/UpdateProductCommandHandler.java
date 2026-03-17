package com.example.Auxo_ECommerce_API.Application.Features.Products.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.UpdateProductCommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Infrustracture.Services.FileStorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service("UpdateProductCommandHandler")
public class UpdateProductCommandHandler implements ICommandHandler<UpdateProductCommand, Object> {
    private final IUnitOfWork _unitOfWork;
    private final FileStorageService fileStorageService; // ← inject
    @Override
    public Result<Object> handle(UpdateProductCommand request) {
        Optional<Product> product = _unitOfWork.Products().findById(request.getId());
        if(product.isEmpty())
            return Result.failure("product not found");

//        if(!Objects.equals(product.get().getCategory().getId(), request.getCategoryId())){
            Optional<Category> category = _unitOfWork.Categories().findById(request.getCategoryId());
            if(category.isEmpty())
                return Result.failure("category not found");
            product.get().setCategory(category.get());

//        }

        // Handle image: upload new one if provided, otherwise keep existing
        String imageUrl = request.getExistingImageUrl();
        if (request.getImageFile() != null && !request.getImageFile().isEmpty()) {
            try {
                // Delete old image from disk
                fileStorageService.delete(product.get().getImageUrl());
                // Save new image
                imageUrl = fileStorageService.save(request.getImageFile());
            } catch (IOException | IllegalArgumentException e) {
                return Result.failure("Image upload failed: " + e.getMessage());
            }
        }
        var updatedProduct = request.toEntity(product.get(),category.get(),request);
        updatedProduct.setImageUrl(imageUrl);

        _unitOfWork.Products().save(updatedProduct);
        _unitOfWork.commit();
        return Result.success(updatedProduct.getId());
    }
}
