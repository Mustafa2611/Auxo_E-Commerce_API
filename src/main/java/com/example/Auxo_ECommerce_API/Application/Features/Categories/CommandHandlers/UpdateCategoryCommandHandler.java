package com.example.Auxo_ECommerce_API.Application.Features.Categories.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.UpdateCategoryCommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Infrustracture.Services.FileStorageService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@Service("UpdateCategoryCommandHandler")
public class UpdateCategoryCommandHandler implements ICommandHandler<UpdateCategoryCommand, Long> {
    private final IUnitOfWork _unitOfWork;
    private final FileStorageService fileStorageService;
    @Override
    public  Result<Long> handle(UpdateCategoryCommand request) {
        Optional< Category> category =  _unitOfWork.Categories().findById(request.getId());
        if(category.isEmpty())
            return Result.failure("category not found");
        if (request.getParentId() != null && request.getParentId().equals(category.get().getId())) {
            return Result.failure("Category cannot be parent of itself");
        }

        if (request.getParentId() != null) {

            Category parent = _unitOfWork
                    .Categories()
                    .getById(request.getParentId());

            if (parent == null) {
                return Result.failure("Parent category not found");
            }

            category.get().setParent(parent);
        }
// Handle image: upload new one if provided, otherwise keep existing
        String imageUrl = request.getExistingImageUrl();
        if (request.getImageFile() != null && !request.getImageFile().isEmpty()) {
            try {
                // Delete old image from disk
                fileStorageService.delete(category.get().getImageUrl());
                // Save new image
                imageUrl = fileStorageService.save(request.getImageFile());
            } catch (IOException | IllegalArgumentException e) {
                return Result.failure("Image upload failed: " + e.getMessage());
            }
        }
        category.get().setNameAr(request.getNameAr());
        category.get().setNameEn(request.getNameEn());
        category.get().setType(request.getType());
        category.get().setImageUrl(imageUrl);
        _unitOfWork.Categories().save(category.get());
        _unitOfWork.commit();
        return Result.success( category.get().getId());
    }
}
