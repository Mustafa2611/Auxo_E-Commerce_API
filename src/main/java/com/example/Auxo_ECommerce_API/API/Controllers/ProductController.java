package com.example.Auxo_ECommerce_API.API.Controllers;

import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.DeleteCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.UpdateCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoryByIdQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.CreateNewProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.DeleteProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.UpdateProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductDetailsDto;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductByIdQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductsPageQuery;
import com.example.Auxo_ECommerce_API.Domain.Entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final IMediator mediator;
    @PostMapping("/create-product")
    public ResponseEntity<Result<UUID>> createProduct(@RequestBody CreateNewProductCommand command) {
        Result<UUID> result = mediator.send(command);

        if (result.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/edit-product")
    public ResponseEntity<Result<UUID>> editProduct(@RequestBody UpdateProductCommand command){
        Result<UUID> result = mediator.send(command);
        if (result.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<Result<UUID>> deleteProduct(@RequestBody DeleteProductCommand command){
        Result<UUID> result = mediator.send(command);
        if (result.isFailure()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @GetMapping("/get-all")
    public Result<List<GetProductsPageDto>> GetAll()
    {
        Result<List<GetProductsPageDto>> result = mediator.send(new GetProductsPageQuery());

        if (result.isFailure()) {
            return Result.failure(result.getError());
        }

        return Result.success(result.getValue());
    }
    @GetMapping("/get-by-id")
    public Result<GetProductDetailsDto> GetById(@RequestParam UUID Id)
    {
        Result<GetProductDetailsDto> result = mediator.send(new GetProductByIdQuery(Id));

        if (result.isFailure()) {
            return Result.failure(result.getError());
        }


        return Result.success(result.getValue());
    }

}