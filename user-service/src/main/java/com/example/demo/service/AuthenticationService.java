package com.example.demo.service;

import com.example.demo.Utils.JWTUtil;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest loginRes)
    {
        String email = loginRes.getEmail();
        String password = loginRes.getPassword();
        if(email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Email is Empty");

        if(password == null || password.trim().isEmpty())
            throw new IllegalArgumentException(("Password is Empty"));

        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty())
            throw new UserNotFoundException("User not found");

        User user = userOptional.get();
         if(!passwordEncoder.matches(password, user.getPassword()))
             throw new InvalidCredentialsException("Invalid credentials");

         Map<String, Object> claims = new HashMap<>();
         claims.put("userId", user.getUserId());
         claims.put("email", email);

        String token = jwtUtil.generateToken(claims);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getUserId());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());

        return new AuthResponse(token, userResponse, jwtUtil.EXPIRATION_TIME);
    }

    public AuthResponse register(RegisterRequest regRes)
    {
        String email = regRes.getEmail();
        String password = regRes.getPassword();
        boolean userExists = userRepository.existsByEmail(email);
        if(userExists)
            throw new UserAlreadyExistsException("User already exists");

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setCreatedAt(LocalDateTime.now());

        try {
            userRepository.save(newUser);
            System.out.println("User saved successfully with ID: " + newUser.getUserId());
        } catch (Exception e) {
            System.out.println("Database error: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", newUser.getUserId());
        claims.put("email", newUser.getEmail());

        String token = jwtUtil.generateToken(claims);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(newUser.getUserId());
        userResponse.setEmail(newUser.getEmail());
        userResponse.setCreatedAt(newUser.getCreatedAt());

        return new AuthResponse(token, userResponse, jwtUtil.EXPIRATION_TIME);
    }
}
