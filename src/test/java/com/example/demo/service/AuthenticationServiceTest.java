package com.example.demo.service;

import com.example.demo.Utils.JWTUtil;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTUtil jwtUtil;

    @InjectMocks
    private AuthenticationService authenticationService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private User user;

    @BeforeEach
    void setUp()
    {
        registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password123");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password123");

        user = new User();
        user.setUserId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");
        user.setCreatedAt(LocalDateTime.now());
    }
    @Test
    void testRegister_Success() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtUtil.generateToken(anyMap())).thenReturn("jwt-token");
        AuthResponse response = authenticationService.register(registerRequest);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
        assertEquals("test@example.com", response.getUser().getEmail());
        verify(userRepository).existsByEmail("test@example.com");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
        verify(jwtUtil).generateToken(anyMap());
    }

    @Test
    void testRegister_UserAlreadyExists() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> {
            authenticationService.register(registerRequest);
        });

        verify(userRepository).existsByEmail("test@example.com");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testLogin_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtUtil.generateToken(anyMap())).thenReturn("jwt-token");
        AuthResponse response = authenticationService.login(loginRequest);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
        assertEquals("test@example.com", response.getUser().getEmail());
        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("password123", "encodedPassword");
        verify(jwtUtil).generateToken(anyMap());
    }

    @Test
    void testLogin_UserNotFound() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            authenticationService.login(loginRequest);
        });

        verify(userRepository).findByEmail("test@example.com");
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtUtil);
    }

    @Test
    void testLogin_InvalidCredentials() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, () -> {
            authenticationService.login(loginRequest);
        });

        verify(userRepository).findByEmail("test@example.com");
        verify(passwordEncoder).matches("password123", "encodedPassword");
        verifyNoInteractions(jwtUtil);
    }

    @Test
    void testLogin_NullEmail() {
        loginRequest.setEmail(null);

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.login(loginRequest);
        });

        verifyNoInteractions(userRepository);
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtUtil);
    }

    @Test
    void testLogin_EmptyPassword() {
        loginRequest.setPassword("");

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.login(loginRequest);
        });

        verifyNoInteractions(userRepository);
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtUtil);
    }
}
