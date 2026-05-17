package com.yp.ahrenix.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String address;

    private Boolean active;

    private Boolean emailVerified;

    private Boolean mfaEnabled;

    private Set<String> roles;

    private LocalDateTime createdAt;

}