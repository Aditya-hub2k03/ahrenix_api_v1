package com.yp.ahrenix.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtBlacklistService {

    private final RedisTemplate<String, Object>
            redisTemplate;

    public void blacklistToken(
            String token,
            long expiration
    ) {

        redisTemplate.opsForValue()
                .set(
                        token,
                        "BLACKLISTED",
                        expiration,
                        TimeUnit.MILLISECONDS
                );
    }

    public boolean isBlacklisted(
            String token
    ) {

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(token)
        );
    }

}