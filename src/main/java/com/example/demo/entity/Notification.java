package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data //for automatic getters, setters.
@Entity
@Table(name = "notification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @PrePersist
    private void OnCreate()
    {
        this.createdAt = LocalDateTime.now();
    }

    public enum NotificationStatus{
        PENDING, SENT, FAILED, READ
    }

}

