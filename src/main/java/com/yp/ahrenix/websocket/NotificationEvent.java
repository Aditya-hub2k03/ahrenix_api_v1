package com.yp.ahrenix.websocket;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEvent {

    private String type;

    private String title;

    private String message;

    private String recipient;

    private LocalDateTime timestamp;

}