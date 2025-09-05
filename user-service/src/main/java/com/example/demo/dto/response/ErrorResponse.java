package com.example.demo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime localDateTime;

    public ErrorResponse(String message, int status)
    {
        this.message = message;
        this.status = status;
        this.localDateTime = LocalDateTime.now();
    }
}
