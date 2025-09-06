package com.example.auth.repository;

import com.example.auth.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    private final TestEntityManager entityManager;
    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(TestEntityManager entityManager, UserRepository userRepository)
    {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Test
    void testFindByEmail_UserExists()
    {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("test@123");
        user.setCreatedAt(LocalDateTime.now());

        entityManager.persistAndFlush(user);

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertEquals("test@123", foundUser.get().getPassword());
    }

    @Test
    void testFindByEmail_UserNotExists() {
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testExistsByEmail_UserExists() {
        User user = new User();
        user.setEmail("existing@example.com");
        user.setPassword("password123");
        user.setCreatedAt(LocalDateTime.now());

        entityManager.persistAndFlush(user);

        boolean exists = userRepository.existsByEmail("existing@example.com");

        assertTrue(exists);
    }

    @Test
    void testExistsByEmail_UserNotExists() {
        boolean exists = userRepository.existsByEmail("nonexistent@example.com");

        assertFalse(exists);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("save@example.com");
        user.setPassword("password123");
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getUserId());
        assertEquals("save@example.com", savedUser.getEmail());
        assertEquals("password123", savedUser.getPassword());
    }

    @Test
    void testFindById() {
        User user = new User();
        user.setEmail("findbyid@example.com");
        user.setPassword("password123");
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = entityManager.persistAndFlush(user);

        Optional<User> foundUser = userRepository.findById(savedUser.getUserId());

        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getUserId(), foundUser.get().getUserId());
        assertEquals("findbyid@example.com", foundUser.get().getEmail());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setEmail("delete@example.com");
        user.setPassword("password123");
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = entityManager.persistAndFlush(user);

        userRepository.deleteById(savedUser.getUserId());

        Optional<User> deletedUser = userRepository.findById(savedUser.getUserId());
        assertFalse(deletedUser.isPresent());
    }

}
