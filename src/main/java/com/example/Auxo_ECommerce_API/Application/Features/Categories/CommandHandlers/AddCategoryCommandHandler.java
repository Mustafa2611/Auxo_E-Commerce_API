package com.example.Auxo_ECommerce_API.Application.Features.Categories.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("AddCategoryCommandHandler")
public class AddCategoryCommandHandler implements ICommandHandler<AddCategoryCommand,Long> {
    private final IUnitOfWork unitOfWork;
    @Override
    public Result<Long> handle(AddCategoryCommand request) {
        // 🔹 If parentId exists → validate & assign parent

        Category category = Category.builder()
                .nameAr(request.getNameAr())
                .nameEn(request.getNameEn())
                .type(request.getType())
                .build() ;
        if (request.getParentId() != null) {

            Category parent = unitOfWork
                    .Categories()
                    .getById(request.getParentId());

            if (parent == null) {
                return Result.failure("Parent category not found");
            }

            category.setParent(parent);
        }
        Category savedCategory = unitOfWork.Categories().save(category);
        Result commitResult = unitOfWork.commit();
        if (commitResult.isFailure()) {
            return Result.failure(commitResult.getError());
        }
        return Result.success(savedCategory.getId());
    }
}
