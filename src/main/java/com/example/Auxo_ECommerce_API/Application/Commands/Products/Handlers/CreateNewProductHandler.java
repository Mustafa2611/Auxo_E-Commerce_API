package com.example.Auxo_ECommerce_API.Application.Commands.Products.Handlers;

import com.example.Auxo_ECommerce_API.Application.Commands.Products.CreateNewProductCommand;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Domain.Interfaces.IUnitOfWork;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CreateNewProductHandler implements ICommandHandler<CreateNewProductCommand, UUID> {

    private final IUnitOfWork unitOfWork;

    @Override
    public Result<UUID> handle(@NotNull CreateNewProductCommand command) {
        Product product = new Product();

        product.setName(command.getDto().getName());
        product.setDescription(command.getDto().getDescription());
        product.setPrice(command.getDto().getPrice());
        product.setStock(command.getDto().getStock());

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
