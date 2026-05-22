package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.OtpVerification;
import com.yp.ahrenix.repository.OtpVerificationRepository;
import com.yp.ahrenix.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpVerificationRepository repository;

    public String generateOtp(
            String email
    ) {

        String otp = OtpUtil.generateOtp();

        OtpVerification entity =
                OtpVerification.builder()
                        .email(email)
                        .otp(otp)
                        .expiryTime(
                                LocalDateTime.now()
                                        .plusMinutes(5)
                        )
                        .verified(false)
                        .build();

        repository.save(entity);

        return otp;
    }

}