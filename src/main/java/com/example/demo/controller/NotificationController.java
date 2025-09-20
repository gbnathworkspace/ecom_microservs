package com.example.auth.controller;

import com.example.auth.dto.request.NotificationRequest;
import com.example.auth.dto.response.NotificationResponse;
import com.example.auth.entity.Notification;
import com.example.auth.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService)
    {
        this.notificationService =  notificationService;//
    }

    @PostMapping("/sendnotification")
    public boolean SendNotification(@RequestBody NotificationRequest notification)
    {
        Notification newNotification = notificationService
                .createNotification(notification.getUserId(), notification.getTitle(), notification.getMessage(), notification.getRecipientEmail());

        return notificationService.sendNotification(newNotification.getId());
    }

    @GetMapping("/getnotificationsbyuser")
    public List<NotificationResponse> GetNotification(@RequestParam UUID userId)
    {
        List<Notification> notificationList = notificationService.getNotificationByUser(userId);
        List<NotificationResponse> responses = new ArrayList<>();
        for (Notification notification : notificationList)
        {
            responses.add(convertToNotificationResponse(notification));
        }
        return responses;
    }

    @PutMapping("/markasread")
    public ResponseEntity<NotificationResponse> MarkAsRead(@RequestParam UUID notificationId)
    {
        try {
            Notification notification = notificationService.markAsRead(notificationId);
            if(notification!=null)
            {
                return ResponseEntity.ok(convertToNotificationResponse(notification));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Or wrap in a meaningful error object

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Consider logging the error and returning a generic message
        }


}

    @GetMapping("/getallnotifications")
    public List<NotificationResponse> GetAllNotifications()
    {
        List<NotificationResponse> responses = new ArrayList<>();
        List<Notification> notificationList = notificationService.getAllNotification();
        for (Notification notification : notificationList)
        {
            responses.add(convertToNotificationResponse(notification));
        }
        return responses;
    }

    private NotificationResponse convertToNotificationResponse(Notification notification) {
        NotificationResponse notificationResponse = new NotificationResponse();
        notificationResponse.setId(notification.getId());
        notificationResponse.setTitle(notification.getTitle());
        notificationResponse.setMessage(notification.getMessage());
        notificationResponse.setIsRead(notification.getStatus() == Notification.NotificationStatus.READ);
        notificationResponse.setCreatedAt(notification.getCreatedAt());
        notificationResponse.setRecipientEmail(notification.getRecipientEmail());
        notificationResponse.setType(notification.getStatus().toString());
        return notificationResponse;
    }
    }
