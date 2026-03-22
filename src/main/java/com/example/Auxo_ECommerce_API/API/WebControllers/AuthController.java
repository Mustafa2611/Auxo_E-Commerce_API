package com.example.Auxo_ECommerce_API.API.WebControllers;

import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
import com.example.Auxo_ECommerce_API.Application.Common.Result;
import com.example.Auxo_ECommerce_API.Application.Features.Categories.Commands.AddCategoryCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.LoginCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.RegisterCommand;
import com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos.LoginResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
        private final IMediator mediator;
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("login", new LoginCommand());

        return "auth/register";
    }

    @PostMapping("api/login")
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginCommand command) {

        Result<LoginResponse> outerResult = mediator.send(command);

        if (outerResult.isFailure()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, null, outerResult.getError()));
        }

        Result<LoginResponse> innerResult = outerResult;

        if (innerResult.isFailure()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, null, innerResult.getError()));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(true, innerResult.getValue(), null)
        );
    }
    @PostMapping("api/register")
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterCommand command) {

        Result<String> outerResult = mediator.send(command);

        if (outerResult.isFailure()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, null, outerResult.getError()));
        }

        Result<String> innerResult = outerResult;

        if (innerResult.isFailure()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, null, innerResult.getError()));
        }

        return ResponseEntity.status(201)
                .body(new ApiResponse<>(true, innerResult.getValue(), null));
    }

    // ─────────────────────────────────────────────────────────────
    // Shared response envelope
    // ─────────────────────────────────────────────────────────────

    record ApiResponse<T>(boolean isSuccess, T value, String error) {}
}
