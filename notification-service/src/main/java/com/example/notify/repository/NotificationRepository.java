package com.example.notify.repository;

import com.example.notify.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByUserid(UUID userId);

    @Override
    Optional<Notification> findById(UUID id);
}
