package com.example.demo.controller;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.request.LoginRequest;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest)
    {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest)
    {
        return authenticationService.register(registerRequest);
    }
    
    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint reached!");
        return "Auth endpoint working!";
    }

}
