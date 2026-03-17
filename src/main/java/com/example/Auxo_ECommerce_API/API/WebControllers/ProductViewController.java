//package com.example.Auxo_ECommerce_API.API.WebControllers;
//
//import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
//import com.example.Auxo_ECommerce_API.Application.Features.Categories.Queries.GetCategoryByIdQuery;
//import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductByIdQuery;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.UUID;
//
//@Controller
//@RequestMapping("/products")
//@AllArgsConstructor
//public class ProductViewController {
//
//    private final IMediator mediator;
//
//    @GetMapping("/{id}")
//    public String details(@PathVariable UUID id, Model model) {
//
//        var product = mediator.send(new GetProductByIdQuery(id)).getValue();
//
//        model.addAttribute("product", product);
//
//        return "product-details";
//    }
//}
