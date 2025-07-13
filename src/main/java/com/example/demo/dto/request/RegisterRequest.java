package com.example.demo.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String email;
    private String password;
}



