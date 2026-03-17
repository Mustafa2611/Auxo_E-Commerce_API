package com.example.Auxo_ECommerce_API.Application.Features.Products.CommandHandlers;

import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.DeleteProductCommand;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service("DeleteProductCommandHandler")
public class DeleteProductCommandHandler implements ICommandHandler<DeleteProductCommand, String> {
    private final IUnitOfWork _unitOfWork;
    @Override
    public Result<String> handle(DeleteProductCommand request) {
        Optional<Product> product = _unitOfWork.Products().findById(request.getId());
        if(product.isEmpty())
            return Result.failure("product not found");

        _unitOfWork.Products().delete(product.get());
        _unitOfWork.commit();
        return Result.success(product.get().getId());
    }
}
