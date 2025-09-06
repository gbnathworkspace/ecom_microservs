package com.example.auth.dto.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String type = "Bearer";
    private UserResponse user;
    private long expiresIn;

    public AuthResponse(String token, UserResponse user, long expiresIn)
    {
        this.token = token;
        this.user = user;
        this.expiresIn = expiresIn;
    }

}
