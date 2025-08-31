package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.entity.Email;
import com.example.demo.entity.Notification;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    EmailService(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMail(Email email)
    {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email.getTo());
            simpleMailMessage.setSubject(email.getSubject());
            simpleMailMessage.setText(email.getBody());
            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

    public boolean sendNotificationMail(Notification notification)
    {
        try
        {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(notification.getRecipientEmail());
            simpleMailMessage.setSubject(notification.getTitle());
            simpleMailMessage.setText(notification.getMessage());
            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), (Object) e.getStackTrace());
            return false;
        }
        return true;
    }
}
