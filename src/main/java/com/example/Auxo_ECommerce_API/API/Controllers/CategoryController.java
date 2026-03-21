//package com.example.Auxo_ECommerce_API.API.Controllers;
//
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.AddCategoryCommand;
//import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
//
//import com.example.Auxo_ECommerce_API.Application.Common.Result;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.DeleteCategoryCommand;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.UpdateCategoryCommand;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDetailsDto;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesQuery;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoryByIdQuery;
//import lombok.AllArgsConstructor;
//import org.hibernate.sql.Delete;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/categories")
//@AllArgsConstructor
//public class CategoryController {
//
//    private final IMediator mediator;
//    @PostMapping("/create-category")
//    public ResponseEntity<String> createCategory(@RequestBody AddCategoryCommand command) {
//        Result<Long> result = mediator.send(command);
//
//        if (result.isFailure()) {
//            return ResponseEntity.badRequest().body(result.getError());
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(result.getValue().toString());
//    }
//
//    @PutMapping("/edit-category")
//    public ResponseEntity<String> editCategory(@RequestBody UpdateCategoryCommand command){
//        Result<Long> result = mediator.send(command);
//        if(result.isFailure())
//            return ResponseEntity.badRequest().body(result.getError());
//        return ResponseEntity.status(HttpStatus.OK).body(result.getValue().toString());
//    }
//
//    @DeleteMapping("/delete-category")
//    public ResponseEntity<String> deleteCategory(@RequestBody DeleteCategoryCommand command){
//        Result<Long> result = mediator.send(command);
//        if(result.isFailure())
//            return ResponseEntity.badRequest().body(result.getError());
//        return ResponseEntity.status(HttpStatus.OK).body(result.getValue().toString());
//    }
//    @GetMapping("/get-all")
//    public Result<List<GetCategoryDto>> GetAll()
//    {
//        Result<List<GetCategoryDto>> result = mediator.send(new GetCategoriesQuery());
//
//        if (result.isFailure()) {
//            return Result.failure(ResponseEntity.badRequest().body(result.getError()).toString());
//        }
//
//        return Result.success(ResponseEntity.ok(result.getValue()));
//    }
//    @GetMapping("/get-by-id")
//    public Result<GetCategoryDto> GetById(@RequestParam Long Id)
//    {
//        GetCategoryByIdQuery query = new GetCategoryByIdQuery();
//        query.setId(Id);
//        Result<GetCategoryDetailsDto> result = mediator.send(query);
//
//        if (result.isFailure()) {
//            return Result.failure(ResponseEntity.badRequest().body(result.getError()).toString());
//        }
//
//        return Result.success(ResponseEntity.ok(result.getValue()));
//    }
//
////    @Autowired
////    private CategoryService categoryService;
////    @GetMapping
////    public List<Category> getCategories() { return categoryService.getAllCategories(); }
////    @PostMapping
////    public Category addCategory(@RequestBody Category category) { return categoryService.saveCategory(category); }
//
//}
//
