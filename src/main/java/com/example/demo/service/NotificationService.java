package com.example.auth.service;

import com.example.auth.entity.Notification;
import com.example.auth.repository.NotificationRepository;
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

    public Notification createNotification(UUID userid, String title, String message, String email)
    {
        Notification notification = Notification.builder()
                .userid(userid)
                .title(title)
                .message(message)
                .recipientEmail(email)
                .status(Notification.NotificationStatus.PENDING)
                .build();

        notificationRepository.save(notification);
        return notification;
    }

    public boolean sendNotification(UUID uuid)
    {
        Notification notification = notificationRepository.getReferenceById(uuid);
        boolean sent = emailService.sendNotificationMail(notification);
        if(sent)
        {
            notification.setStatus(Notification.NotificationStatus.SENT);
        }
        else{
            notification.setStatus(Notification.NotificationStatus.FAILED);
        }
        notificationRepository.save(notification);
        return sent;
    }

    public List<Notification> getNotificationByUser(UUID userId)
    {
        return notificationRepository.findByUserid(userId); //test
    }

    public Notification markAsRead(UUID notificationId)
    {
        Optional<Notification> notification = notificationRepository.findById(notificationId);
        notification.ifPresent(
            value -> {
                value.setStatus(Notification.NotificationStatus.READ);
                notificationRepository.save(value);
            }
        );
        return notification.orElse(null);
    }

    public List<Notification> getAllNotification()
    {
        return notificationRepository.findAll();
    }
}
