package com.yp.ahrenix.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponse {

    private Long id;

    private String action;

    private String entityName;

    private String username;

    private String details;

    private String ipAddress;

    private LocalDateTime createdAt;

}
