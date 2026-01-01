package com.example.Auxo_ECommerce_API.Application.Commands.Categories.Handlers;

import com.example.Auxo_ECommerce_API.Application.Commands.Categories.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("AddCategoryCommandHandler")
public class AddCategoryCommandHandler implements ICommandHandler<AddCategoryCommand,Long> {
    private IUnitOfWork unitOfWork;
    @Override
    public Result<Long> handle(AddCategoryCommand request) {
        Category category = new Category();
        category.setNameAr(request.getDto().getNameAr());
        category.setNameEn(request.getDto().getNameEn());

        Category savedCategory = unitOfWork.Categories().save(category);
        Result commitResult = unitOfWork.commit();
        if (commitResult.isFailure()) {
            return Result.failure(commitResult.getError());
        }
        return Result.success(savedCategory.getId());
    }
}
