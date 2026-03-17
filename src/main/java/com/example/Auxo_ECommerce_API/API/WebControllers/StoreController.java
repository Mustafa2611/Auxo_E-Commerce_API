package com.example.Auxo_ECommerce_API.API.WebControllers;

import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Features.LandingPages.Queries.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class StoreController {

    private final IMediator mediator;


    // In StoreController — add this helper and call it in every route
    private void addNavCategories(Model model) {
        GetCategoriesTreeQuery treeQ = new GetCategoriesTreeQuery();
        var cats = mediator.send(treeQ);
        model.addAttribute("navCategories", cats.getValue());
    }

    // ── Landing page ──────────────────────────────────────────────────────
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        addNavCategories(model);
        var categories = mediator.send(new GetCategoriesCardsQuery());
        var featured   = mediator.send(new GetMostViewedProductsQuery());

        model.addAttribute("categories", categories.getValue());
        model.addAttribute("featuredProducts", featured.getValue());
        return "store/home";
    }

    // ── All products with filters ─────────────────────────────────────────
    @GetMapping("/products")
    public String products(Model model) {
        addNavCategories(model);
        // Load sidebar categories (full tree)
        var categories = mediator.send(new GetCategoriesCardsQuery());
        // Load all main categories with their tree for sidebar
        var mainCats   = mediator.send(new GetCategoriesTreeQuery()); // see below

        // Initial load — no filters
        GetProductsWithFiltrationQuery query = new GetProductsWithFiltrationQuery();
        var products = mediator.send(query);

        model.addAttribute("products", products.getValue());
        model.addAttribute("mainCategories", mainCats.getValue());
        return "store/products";
    }

    // ── AJAX: filter products ─────────────────────────────────────────────
    @GetMapping("/products/filter")
    @ResponseBody
    public Object filterProducts(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) List<Long> categoryIds,
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "0") double maxPrice) {

        GetProductsWithFiltrationQuery query = new GetProductsWithFiltrationQuery();
        query.setSearchKeyword(search);
        query.setCategoriesIds(categoryIds);
        query.setMinPrice(minPrice);
        query.setMaxPrice(maxPrice);

        var result = mediator.send(query);
        return result.getValue() != null ? result.getValue() : List.of();
    }

    // ── Product details ───────────────────────────────────────────────────
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable UUID id, Model model) {
        addNavCategories(model);
        GetProductDetailsQuery query = new GetProductDetailsQuery();
        query.setId(id);
        var result = mediator.send(query);

        if (result.isFailure())
            return "redirect:/products";

        model.addAttribute("product", result.getValue());
        return "store/product-details";
    }
}
