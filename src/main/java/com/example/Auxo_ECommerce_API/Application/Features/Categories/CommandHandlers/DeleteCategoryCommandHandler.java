package com.example.Auxo_ECommerce_API.Application.Features.Categories.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommand;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.DeleteCategoryCommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Category;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service("DeleteCategoryCommandHandler")
public class DeleteCategoryCommandHandler  implements ICommandHandler<DeleteCategoryCommand, Long> {
    private final IUnitOfWork _unitOfWork;
    @Override
    public Result<Long> handle(DeleteCategoryCommand request) {
        Optional<Category> category = _unitOfWork.Categories().findById(request.getId());
        if(category.isEmpty())
            return Result.failure("category not found");
        _unitOfWork.Categories().delete(category.get());
        _unitOfWork.commit();
        return Result.success(category.get().getId());
    }
}
