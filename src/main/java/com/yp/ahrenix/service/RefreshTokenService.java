package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.RefreshToken;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.exception.InvalidTokenException;
import com.yp.ahrenix.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiration;

    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(
                        LocalDateTime.now()
                                .plusSeconds(refreshExpiration / 1000)
                )
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken =
                refreshTokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new InvalidTokenException(
                                        "Refresh token not found"
                                )
                        );

        if (refreshToken.getRevoked()) {
            throw new InvalidTokenException("Refresh token revoked");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {

            refreshTokenRepository.delete(refreshToken);

            throw new InvalidTokenException("Refresh token expired");
        }

        return refreshToken;
    }

    public void revokeTokens(User user) {

        refreshTokenRepository.deleteByUser(user);
    }

}