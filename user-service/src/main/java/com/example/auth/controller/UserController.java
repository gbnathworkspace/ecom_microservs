package com.example.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/profile")
    public Map<String, Object> getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Protected endpoint accessed successfully");
        response.put("user", auth.getName());
        response.put("authorities", auth.getAuthorities());
        
        return response;
    }

    @GetMapping("/dashboard")
    public Map<String, String> getDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to your dashboard!");
        response.put("authenticatedUser", auth.getName());
        
        return response;
    }
}