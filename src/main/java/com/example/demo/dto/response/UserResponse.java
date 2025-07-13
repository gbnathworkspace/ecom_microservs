package com.example.demo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
