package com.example.demo.dto.request;

import java.util.UUID;

public class NotificationRequest {
    private UUID userId;
    private String title;
    private String message;
    private String recipientEmail;
    private String type;// EMAIL, SMS, IN_APP
}
