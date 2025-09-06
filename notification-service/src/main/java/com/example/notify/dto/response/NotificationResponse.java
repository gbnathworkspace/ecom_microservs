package com.example.notify.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
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


