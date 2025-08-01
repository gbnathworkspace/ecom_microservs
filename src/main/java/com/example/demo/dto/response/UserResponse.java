package com.example.demo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
