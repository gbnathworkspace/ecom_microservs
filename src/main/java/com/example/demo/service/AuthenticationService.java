package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthResponse login(LoginRequest loginRes)
    {
        String token = null;
        UserResponse userResponse = null;
        long expiresIn = 0;
        return new AuthResponse(token, userResponse, expiresIn);
    }

    public AuthResponse register(RegisterRequest regRes)
    {
        String token = null;
        UserResponse userResponse = null;
        long expiresIn = 0;
        return new AuthResponse(token, userResponse, expiresIn);
    }
}
