package com.example.demo.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class NotificationResponse {
    private UUID id;
    private UUID userId;
    private String title;
    private String message;
    private String recipientEmail;
    private String type;
    private Boolean isRead;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}


