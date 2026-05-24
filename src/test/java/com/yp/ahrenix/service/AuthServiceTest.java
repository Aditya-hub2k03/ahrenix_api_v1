package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.RegisterRequest;
import com.yp.ahrenix.entities.Role;
import com.yp.ahrenix.enums.RoleType;
import com.yp.ahrenix.exception.DuplicateResourceException;
import com.yp.ahrenix.repository.RoleRepository;
import com.yp.ahrenix.repository.UserRepository;
import com.yp.ahrenix.util.JwtUtil;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldRegisterUserSuccessfully() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .fullName("Test User")
                        .email("test@ahrenix.com")
                        .password("Password@123")
                        .phone("9876543210")
                        .build();

        Role role =
                Role.builder()
                        .name(RoleType.ROLE_USER)
                        .build();

        when(userRepository.existsByEmail(
                request.getEmail()))
                .thenReturn(false);

        when(userRepository.existsByPhone(
                request.getPhone()))
                .thenReturn(false);

        when(roleRepository.findByName(
                RoleType.ROLE_USER))
                .thenReturn(Optional.of(role));

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        assertDoesNotThrow(() ->
                authService.register(request));
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {

        RegisterRequest request =
                RegisterRequest.builder()
                        .email("existing@ahrenix.com")
                        .build();

        when(userRepository.existsByEmail(
                request.getEmail()))
                .thenReturn(true);

        assertThrows(
                DuplicateResourceException.class,
                () -> authService.register(request)
        );
    }

}