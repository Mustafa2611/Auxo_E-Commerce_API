package com.example.Auxo_ECommerce_API.API.WebControllers;

import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.DeleteCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.UpdateCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Dtos.GetCategoryDto;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.*;
import com.example.Auxo_ECommerce_API.Domain.Enums.CategoryType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
@AllArgsConstructor
public class AdminCategoriesController {

    private final IMediator mediator;

    // 🔹 Get All Categories Page
//    @GetMapping({"", "/", "/index"})  // handles all three
//    public String getAllCategories(
//            @RequestParam(defaultValue = "cards") String view,
//            Model model) {
//        var result = mediator.send(new GetCategoriesQuery());
//
//        model.addAttribute("categories", result.getValue());
//        model.addAttribute("viewMode", view);
//        return "admin/categories/index";
//    }

    @GetMapping({"", "/", "/index"})
    public String getAllCategories(
            @RequestParam(defaultValue = "cards") String view,
            Model model) {
        var result = mediator.send(new GetCategoriesQuery());

        model.addAttribute("categories", result.getValue());
        model.addAttribute("viewMode", view);
        model.addAttribute("activePage", "categories");   // ← add this
        model.addAttribute("pageTitle", "Categories");    // ← add this
        return "admin/categories/index";
    }

    // 🔹 Show Create Page
    @GetMapping("/create")
    public String showCreateForm(Model model) {

        // Get MAIN categories for Section parent dropdown
        GetCategoriesByTypeQuery mainQuery = new GetCategoriesByTypeQuery();
        mainQuery.setType(CategoryType.MAIN);
        mainQuery.setExcludeId(null);
        var mainCategories = mediator.send(mainQuery);

        // Get SECTION categories for SubSection parent dropdown
        GetCategoriesByTypeQuery sectionQuery = new GetCategoriesByTypeQuery();
        sectionQuery.setType(CategoryType.SECTION);
        sectionQuery.setExcludeId(null);
        var sectionCategories = mediator.send(sectionQuery);

        model.addAttribute("category", new AddCategoryCommand());
        model.addAttribute("mainCategories", mainCategories.getValue());
        model.addAttribute("sectionCategories", sectionCategories.getValue());
        model.addAttribute("activePage", "categories");
        model.addAttribute("pageTitle", "Create Category");
        return "admin/categories/create";
    }

    // 🔹 Handle Create
    @PostMapping("/create")
    public String createCategory(@ModelAttribute AddCategoryCommand command) {

        mediator.send(command);
        return "redirect:/admin/categories/index";
    }

    // 🔹 Show Edit Page
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        GetCategoryByIdQuery query = new GetCategoryByIdQuery();
        query.setId(id);
        var result = mediator.send(query);

        if (result.isFailure())
            return "redirect:/admin/categories/index";

        // Exclude current from both dropdowns
        GetCategoriesByTypeQuery mainQuery = new GetCategoriesByTypeQuery();
        mainQuery.setType(CategoryType.MAIN);
        mainQuery.setExcludeId(id);
        var mainCategories = mediator.send(mainQuery);

        GetCategoriesByTypeQuery sectionQuery = new GetCategoriesByTypeQuery();
        sectionQuery.setType(CategoryType.SECTION);
        sectionQuery.setExcludeId(id);
        var sectionCategories = mediator.send(sectionQuery);

        model.addAttribute("category", result.getValue());
        model.addAttribute("mainCategories", mainCategories.getValue());
        model.addAttribute("sectionCategories", sectionCategories.getValue());
        model.addAttribute("activePage", "categories");
        model.addAttribute("pageTitle", "Edit Category");
        return "admin/categories/edit";
    }

    // 🔹 Handle Edit
    @PostMapping("/edit")
    public String editCategory(@ModelAttribute UpdateCategoryCommand command) {

        mediator.send(command);
        return "redirect:/admin/categories/index";
    }

    // 🔹 Delete
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {

        DeleteCategoryCommand command = new DeleteCategoryCommand();
        command.setId(id);

        mediator.send(command);
        return "redirect:/admin/categories/index";
    }

    @GetMapping("/details/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {

        GetCategoryByIdQuery query = new GetCategoryByIdQuery();
        query.setId(id);
        var result = mediator.send(query);

        if (result.isFailure())
            return "redirect:/admin/categories/index";

        model.addAttribute("category", result.getValue());
        model.addAttribute("subCategories", result.getValue().getSubCategories());
        model.addAttribute("activePage", "categories");
        model.addAttribute("pageTitle", result.getValue().getNameEn());
        return "admin/categories/details";
    }

    @GetMapping("/children/{parentId}")
    @ResponseBody
    public List<GetCategoryDto> getChildrenByParent(@PathVariable Long parentId) {
        var result = mediator.send(new GetChildrenByParentIdQuery(parentId));
        return result.getValue() != null ? result.getValue() : List.of();
    }

    // Get parent of a category (used by edit page pre-selection)
    @GetMapping("/parent/{id}")
    @ResponseBody
    public ResponseEntity<GetCategoryDto> getParent(@PathVariable Long id) {
        var result = mediator.send(new GetCategoryByIdQuery(id));

        if (result.isFailure() || result.getValue() == null)
            return ResponseEntity.notFound().build();

        Long parentId = result.getValue().getParentId();
        if (parentId == null)
            return ResponseEntity.notFound().build();

        var parentResult = mediator.send(new GetCategoryByIdQuery(parentId));

        if (parentResult.isFailure() || parentResult.getValue() == null)
            return ResponseEntity.notFound().build();

        var p = parentResult.getValue();
        return ResponseEntity.ok(new GetCategoryDto(
                p.getId(),
                p.getNameAr(),
                p.getNameEn(),
                p.getParentId(),
                p.getParentNameAr(),
                p.getParentNameEn(),
                p.getType(),
                p.getImageUrl()
        ));
    }
//    @GetMapping("/parent/{id}")
//    @ResponseBody
//    public GetCategoryDto getParent(@PathVariable Long id) {
//        var result = mediator.send(new GetCategoryByIdQuery(id));
//        if (result.isFailure() || result.getValue().getParentId() == null)
//            return null;
//
//        GetCategoryByIdQuery parentQuery = new GetCategoryByIdQuery();
//        parentQuery.setId(result.getValue().getParentId());
//        var parent = mediator.send(parentQuery);
//        return parent.isSuccess() ? new GetCategoryDto(
//                parent.getValue().getId(),
//                parent.getValue().getNameAr(),
//                parent.getValue().getNameEn(),
//                null, null, null,
//                parent.getValue().getType()
//        ) : null;
//    }
}