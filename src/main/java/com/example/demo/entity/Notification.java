package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @Column(name = "userId")
    private UUID userid;

    private String title;
    private String message;
    private String recipientEmail;
    private LocalDateTime createdAt;
    private NotificationStatus status;

}

enum NotificationStatus{
    PENDING, SENT, FAILED, READ
}