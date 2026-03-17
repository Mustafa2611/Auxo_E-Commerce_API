package com.example.Auxo_ECommerce_API.API.WebControllers;

import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesByTypeQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesDropdownQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesGroupedQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.CreateNewProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.DeleteProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Commands.UpdateProductCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Dtos.GetProductsPageDto;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductByIdQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductsPageQuery;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/products")
@AllArgsConstructor
public class AdminProductsController {

    private final IMediator mediator;

    // 🔹 Get All Products (with optional category filter)
//    @GetMapping({"", "/", "/index"})
//    public String getAllProducts(
//            @RequestParam(required = false) Long categoryId,
//            @RequestParam(defaultValue = "cards") String view,
//            Model model) {
//
//        GetProductsPageQuery query = new GetProductsPageQuery();
//        query.setCategoryId(categoryId);
//        var result = mediator.send(query);
//
//        // Get all categories for filter dropdown
//        GetCategoriesDropdownQuery categoriesQuery = new GetCategoriesDropdownQuery();
//        categoriesQuery.setCurrentId(null);
//        var categories = mediator.send(categoriesQuery);
//
//        model.addAttribute("products", result.getValue());
//        model.addAttribute("categories", categories.getValue());
//        model.addAttribute("selectedCategoryId", categoryId);
//        model.addAttribute("viewMode", view);
//        model.addAttribute("activePage", "products");
//        model.addAttribute("pageTitle", "Products");
//        return "admin/products/index";
//    }
    @GetMapping({"", "/", "/index"})
    public String getAllProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "cards") String view,
            Model model) {

        // Products filtered by categoryId
        GetProductsPageQuery query = new GetProductsPageQuery();
        query.setCategoryId(categoryId);
        var products = mediator.send(query);

        // Only MAIN categories for the top-level dropdown
        GetCategoriesByTypeQuery mainQuery = new GetCategoriesByTypeQuery();
        mainQuery.setType(CategoryType.MAIN);
        mainQuery.setExcludeId(null);
        var mainCategories = mediator.send(mainQuery);

        model.addAttribute("products", products.getValue());
        model.addAttribute("mainCategories", mainCategories.getValue());
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("viewMode", view);
        model.addAttribute("activePage", "products");
        model.addAttribute("pageTitle", "Products");
        return "admin/products/index";
    }
    @GetMapping("/filter")
    @ResponseBody
    public List<GetProductsPageDto> filterProducts(@RequestParam(required = false) Long categoryId) {
        GetProductsPageQuery query = new GetProductsPageQuery();
        query.setCategoryId(categoryId);
        var result = mediator.send(query);
        return result.getValue() != null ? result.getValue() : List.of();
    }
    // 🔹 Show Create Page
    @GetMapping("/create")
    public String showCreateForm(Model model) {

        GetCategoriesByTypeQuery mainQuery = new GetCategoriesByTypeQuery();
        mainQuery.setType(CategoryType.MAIN);
        mainQuery.setExcludeId(null);
        var mainCategories = mediator.send(mainQuery);

        model.addAttribute("product", new CreateNewProductCommand());
        model.addAttribute("mainCategories", mainCategories.getValue());
        model.addAttribute("activePage", "products");
        model.addAttribute("pageTitle", "Create Product");
        return "admin/products/create";
    }

    // 🔹 Handle Create
//    @PostMapping("/create")
//    public String createProduct(@ModelAttribute CreateNewProductCommand command) {
//        mediator.send(command);
//        return "redirect:/admin/products/index";
//    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createProduct(
            @ModelAttribute CreateNewProductCommand command,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        command.setImageFile(imageFile);
        mediator.send(command);
        return "redirect:/admin/products/index";
    }
    // 🔹 Show Edit Page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        GetProductByIdQuery query = new GetProductByIdQuery();
        query.setId(id);
        var result = mediator.send(query);

        if (result.isFailure())
            return "redirect:/admin/products/index";

//        GetCategoriesGroupedQuery groupedQuery = new GetCategoriesGroupedQuery();
//        groupedQuery.setExcludeId(null);
//        var grouped = mediator.send(groupedQuery);
        GetCategoriesByTypeQuery mainQuery = new GetCategoriesByTypeQuery();
        mainQuery.setType(CategoryType.MAIN);
        mainQuery.setExcludeId(null);
        var mainCategories = mediator.send(mainQuery);

        // Map details dto → update command to pre-fill the form
        UpdateProductCommand command = new UpdateProductCommand();
        command.setId(result.getValue().getId());
        command.setNameEn(result.getValue().getNameEn());
        command.setNameAr(result.getValue().getNameAr());
        command.setDescriptionEn(result.getValue().getDescriptionEn());
        command.setDescriptionAr(result.getValue().getDescriptionAr());
        command.setInstallmentDetails(result.getValue().getInstallmentDetails());
        command.setPrice(result.getValue().getPrice());
        command.setStock(result.getValue().getStock());
        command.setExistingImageUrl(result.getValue().getImageUrl()); // ← add this line
        command.setCategoryId(result.getValue().getCategoryId());
        command.setCategoryType(result.getValue().getCategoryType()); // ← add this

        model.addAttribute("product", command);
//        model.addAttribute("groupedCategories", grouped.getValue());
        model.addAttribute("mainCategories", mainCategories.getValue());

        model.addAttribute("activePage", "products");
        model.addAttribute("pageTitle", "Edit Product");
        return "admin/products/edit";
    }

    // 🔹 Handle Edit
//    @PostMapping("/edit")
//    public String editProduct(@ModelAttribute UpdateProductCommand command) {
//        mediator.send(command);
//        return "redirect:/admin/products/index";
//    }
    @PostMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String editProduct(
            @ModelAttribute UpdateProductCommand command,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        command.setImageFile(imageFile);
        mediator.send(command);
        return "redirect:/admin/products/index";
    }
    // 🔹 View Details
    @GetMapping("/details/{id}")
    public String viewDetails(@PathVariable UUID id, Model model) {
        GetProductByIdQuery query = new GetProductByIdQuery();
        query.setId(id);
        var result = mediator.send(query);

        if (result.isFailure())
            return "redirect:/admin/products/index";

        model.addAttribute("product", result.getValue());
        model.addAttribute("activePage", "products");
        model.addAttribute("pageTitle", result.getValue().getNameEn());
        return "admin/products/details";
    }

    // 🔹 Delete
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        DeleteProductCommand command = new DeleteProductCommand();
        command.setId(id);
        mediator.send(command);
        return "redirect:/admin/products/index";
    }
}
