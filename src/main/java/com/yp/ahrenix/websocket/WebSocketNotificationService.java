package com.yp.ahrenix.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification(
            String recipient,
            String title,
            String message,
            String type
    ) {

        NotificationEvent event =
                NotificationEvent.builder()
                        .recipient(recipient)
                        .title(title)
                        .message(message)
                        .type(type)
                        .timestamp(LocalDateTime.now())
                        .build();

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + recipient,
                event
        );
    }

}