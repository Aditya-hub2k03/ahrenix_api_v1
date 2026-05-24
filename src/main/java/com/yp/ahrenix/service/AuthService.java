package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.LoginRequest;
import com.yp.ahrenix.dto.request.RefreshTokenRequest;
import com.yp.ahrenix.dto.request.RegisterRequest;
import com.yp.ahrenix.dto.response.AuthResponse;
import com.yp.ahrenix.dto.response.UserResponse;
import com.yp.ahrenix.entities.RefreshToken;
import com.yp.ahrenix.entities.Role;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.enums.RoleType;
import com.yp.ahrenix.exception.DuplicateResourceException;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.repository.RefreshTokenRepository;
import com.yp.ahrenix.repository.RoleRepository;
import com.yp.ahrenix.repository.UserRepository;
import com.yp.ahrenix.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }

        if (userRepository.existsByPhone(request.getPhone())) {

            throw new DuplicateResourceException(
                    "Phone number already exists"
            );
        }

        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Default role not found"
                        )
                );

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .phone(request.getPhone())
                .address(request.getAddress())
                .active(true)
                .emailVerified(false)
                .mfaEnabled(false)
                .roles(Set.of(userRole))
                .build();

        User savedUser = userRepository.save(user);

        String accessToken =
                jwtUtil.generateToken(savedUser.getEmail());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(savedUser);

        return buildAuthResponse(
                savedUser,
                accessToken,
                refreshToken.getToken()
        );
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        String accessToken =
                jwtUtil.generateToken(user.getEmail());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return buildAuthResponse(
                user,
                accessToken,
                refreshToken.getToken()
        );
    }

    public AuthResponse refreshToken(
            RefreshTokenRequest request
    ) {

        RefreshToken oldToken =
        refreshTokenService.verifyRefreshToken(
                request.getRefreshToken()
        );

RefreshToken newToken =
        refreshTokenService.rotateRefreshToken(
                oldToken
        );

        User user = newToken.getUser();

        String accessToken =
                jwtUtil.generateToken(user.getEmail());

        return buildAuthResponse(
                user,
                accessToken,
                newToken.getToken()
        );
    }

    public void logout(String refreshToken) {

        RefreshToken token =
                refreshTokenService.verifyRefreshToken(refreshToken);

        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }

    private AuthResponse buildAuthResponse(
            User user,
            String accessToken,
            String refreshToken
    ) {

        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .active(user.getActive())
                .emailVerified(user.getEmailVerified())
                .mfaEnabled(user.getMfaEnabled())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role ->
                                        role.getName().name())
                                .collect(Collectors.toSet())
                )
                .createdAt(user.getCreatedAt())
                .build();

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .user(userResponse)
                .build();
    }

}