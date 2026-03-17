//package com.example.Auxo_ECommerce_API.API.WebControllers;
//
//import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
//import com.example.Auxo_ECommerce_API.Application.Features.Products.Queries.GetProductsPageQuery;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/")
//@AllArgsConstructor
//public class HomeController {
//    private final IMediator mediator;
//
//
//
//    @GetMapping
//    public String home(Model model) {
//
//
//        var products = mediator.send(new GetProductsPageQuery()).getValue();
//
//        model.addAttribute("products", products);
//
//        return "home"; // refers to home.html
//    }
//}
//
