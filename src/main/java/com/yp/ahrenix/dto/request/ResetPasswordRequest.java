package com.yp.ahrenix.dto.request;

import lombok.*;

@Getter
@Setter
public class ResetPasswordRequest {

    private String email;

    private String otp;

    private String newPassword;
}