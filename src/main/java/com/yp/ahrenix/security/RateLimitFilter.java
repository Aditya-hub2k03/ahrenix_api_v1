package com.yp.ahrenix.security;

import io.github.bucket4j.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter
        extends OncePerRequestFilter {

    private final Map<String, Bucket> cache =
            new ConcurrentHashMap<>();

    private Bucket createNewBucket() {

        Bandwidth limit =
                Bandwidth.classic(
                        100,
                        Refill.greedy(
                                100,
                                Duration.ofMinutes(1)
                        )
                );

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
            throws ServletException, IOException {

        String ip =
                request.getRemoteAddr();

        Bucket bucket =
                cache.computeIfAbsent(
                        ip,
                        k -> createNewBucket()
                );

        if(bucket.tryConsume(1)) {

            filterChain.doFilter(
                    request,
                    response
            );

        } else {

            response.setStatus(429);

            response.getWriter().write(
                    "Too many requests"
            );
        }
    }

}