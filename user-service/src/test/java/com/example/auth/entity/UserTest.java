package com.example.auth.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testUserCreation() {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setPassword("test@123");
        user.setCreatedAt(LocalDateTime.now());

        assertNotNull(user.getUserId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("test@123", user.getPassword());
        assertNotNull(user.getCreatedAt());
    }

    @Test
    void testUserEquality() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        User user1 = new User();
        user1.setUserId(userId);
        user1.setEmail("test@example.com");
        user1.setPassword("password123");
        user1.setCreatedAt(now);

        User user2 = new User();
        user2.setUserId(userId);
        user2.setEmail("test@example.com");
        user2.setPassword("password123");
        user2.setCreatedAt(now);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserToString() {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setCreatedAt(LocalDateTime.now());

        String userString = user.toString();

        assertTrue(userString.contains("test@example.com"));
        assertTrue(userString.contains("User"));
    }

    @Test
    void testUserSettersAndGetters() {
        User user = new User();
        UUID userId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now();

        user.setUserId(userId);
        user.setEmail("test@example.com");
        user.setPassword("securePassword");
        user.setCreatedAt(createdAt);

        assertEquals(userId, user.getUserId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("securePassword", user.getPassword());
        assertEquals(createdAt, user.getCreatedAt());
    }

    @Test
    void testUserWithNullValues() {
        User user = new User();

        assertNull(user.getUserId());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getCreatedAt());
    }
}
