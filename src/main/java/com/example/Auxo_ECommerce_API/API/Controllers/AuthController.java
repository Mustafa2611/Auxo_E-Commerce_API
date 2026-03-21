//package com.example.Auxo_ECommerce_API.API.Controllers;
//
//import com.example.Auxo_ECommerce_API.Application.Common.IMediator;
//import com.example.Auxo_ECommerce_API.Application.Common.Result;
//import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.LoginCommand;
//import com.example.Auxo_ECommerce_API.Application.Features.Users.Commands.RegisterCommand;
//import com.example.Auxo_ECommerce_API.Application.Features.Users.Dtos.LoginResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/Auth")
//@AllArgsConstructor
//public class AuthController {
//    private final IMediator mediator;
//    @PostMapping("/register")
//    public ResponseEntity<Result<UUID>> Registeration(@RequestBody RegisterCommand command){
//        Result<UUID> result = mediator.send(command);
//        if(result.isFailure())
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//    @PostMapping("/login")
//    public ResponseEntity<Result<LoginResponse>> Login(@RequestBody LoginCommand command){
//        Result<LoginResponse> result = mediator.send(command);
//
//        if(result.isFailure())
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
////
////    @GetMapping
////    public List<User> getUsers() {
////        return userService.getAllUsers();
////    }
////
////    @PostMapping
////    public User addUser(@RequestBody User user) {
////        return userService.saveUser(user);
////    }
//}
