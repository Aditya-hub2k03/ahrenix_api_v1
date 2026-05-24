package com.yp.ahrenix.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationWebSocketHandler {

    @EventListener
    public void handleSessionConnected(
            SessionConnectedEvent event
    ) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(
                        event.getMessage()
                );

        log.info(
                "WebSocket Connected: sessionId={}",
                accessor.getSessionId()
        );
    }

    @EventListener
    public void handleSessionDisconnected(
            SessionDisconnectEvent event
    ) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(
                        event.getMessage()
                );

        log.info(
                "WebSocket Disconnected: sessionId={}",
                accessor.getSessionId()
        );
    }

    @EventListener
    public void handleSessionSubscribe(
            SessionSubscribeEvent event
    ) {

        StompHeaderAccessor accessor =
                StompHeaderAccessor.wrap(
                        event.getMessage()
                );

        log.info(
                "WebSocket Subscription: sessionId={}, destination={}",
                accessor.getSessionId(),
                accessor.getDestination()
        );
    }

}