package com.example.demo.service;

import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    NotificationService(NotificationRepository notificationRepository, EmailService emailService)
    {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    //Business Logics
}
