package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    NotificationService(NotificationRepository notificationRepository, EmailService emailService)
    {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(String title, String message, String email)
    {
        Notification notification = Notification.builder()
                .title(title)
                .message(message)
                .recipientEmail(email)
                .build();

        notificationRepository.save(notification);
        return notification;
    }

    public boolean sendNotification(UUID uuid)
    {
        Notification notification = notificationRepository.getReferenceById(uuid);
        emailService.sendNotificationMail(notification);
        return true;
    }

    public List<Notification> getNotificationByUser(UUID userId)
    {
        return notificationRepository.findByUserid(userId);
    }

    public void markAsRead(UUID notificationId)
    {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(
            value -> {
                value.setStatus(Notification.NotificationStatus.READ);
                notificationRepository.save(value);
            }
        );
    }

    public List<Notification> getAllNotification()
    {
        return notificationRepository.findAll();
    }
}
