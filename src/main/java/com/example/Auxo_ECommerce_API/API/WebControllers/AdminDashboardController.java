package com.example.Auxo_ECommerce_API.API.WebControllers;


import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoriesQuery;
import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductsPageQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminDashboardController {

    private final IMediator mediator;

    @GetMapping({"", "/"})
    public String dashboard(Model model) {
        var categories = mediator.send(new GetCategoriesQuery());

        GetProductsPageQuery productsQuery = new GetProductsPageQuery();
        productsQuery.setCategoryId(null);
        var products = mediator.send(productsQuery);

        model.addAttribute("activePage", "dashboard");
        model.addAttribute("pageTitle", "Dashboard");
        model.addAttribute("totalCategories",
                categories.getValue() != null ? categories.getValue().size() : 0);

        model.addAttribute("totalProducts",
                products.getValue() != null ? products.getValue().size() : 0);
        // Add totalProducts, totalOrders later when you have those queries
        return "admin/dashboard";
    }
}
