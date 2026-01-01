package com.example.Auxo_ECommerce_API.API.Controllers;

import com.example.Auxo_ECommerce_API.Application.Commands.Categories.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Commands.Categories.Handlers.AddCategoryCommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.ICommandHandler;
import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Common.IQueryHandler;

import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Dtos.Cateories.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Queries.Categories.GetCategoriesQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final IMediator mediator;
    @PostMapping("/create-category")
    public ResponseEntity<String> createProduct(@RequestBody AddCategoryCommand command) {
        Result<Long> result = mediator.send(command);

        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result.getError());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result.getValue().toString());
    }

    @GetMapping("/get-all")
    public Result<List<GetCategoryDto>> GetAll()
    {
        Result<List<GetCategoryDto>> result = mediator.send(new GetCategoriesQuery());

        if (result.isFailure()) {
            return Result.failure(ResponseEntity.badRequest().body(result.getError()).toString());
        }

        return Result.success(ResponseEntity.ok(result.getValue()));
    }

//    @Autowired
//    private CategoryService categoryService;
//    @GetMapping
//    public List<Category> getCategories() { return categoryService.getAllCategories(); }
//    @PostMapping
//    public Category addCategory(@RequestBody Category category) { return categoryService.saveCategory(category); }

}

