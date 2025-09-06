package com.example.auth.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequest {
    private UUID userId;
    private String title;
    private String message;
    private String recipientEmail;
    private String type;// EMAIL, SMS, IN_APP
}
