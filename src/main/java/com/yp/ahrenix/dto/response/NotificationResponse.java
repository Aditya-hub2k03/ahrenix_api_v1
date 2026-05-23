package com.yp.ahrenix.dto.response;

import com.yp.ahrenix.enums.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private NotificationType type;

    private String title;

    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;

    private Long userId;

    private String userName;

}