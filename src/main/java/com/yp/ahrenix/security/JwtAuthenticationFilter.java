package com.yp.ahrenix.security;

import com.yp.ahrenix.service.JwtBlacklistService;
import com.yp.ahrenix.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtBlacklistService jwtBlacklistService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");

        String token = null;

        String email = null;

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            token = bearerToken.substring(7);

            email = jwtUtil.getEmailFromToken(token);
        }

        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(email);

        
                if(jwtBlacklistService.isBlacklisted(token)) {
                        filterChain.doFilter(request, response);
                        return;
                
                }

            if (jwtUtil.validateToken(token)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}