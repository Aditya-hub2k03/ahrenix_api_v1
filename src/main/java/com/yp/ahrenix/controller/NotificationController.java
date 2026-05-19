package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.entities.Notification;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.NotificationService;
import com.yp.ahrenix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<Notification>>>
    getMyNotifications(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        List<Notification> notifications =
                notificationService.getUserNotifications(user);

        return ResponseEntity.ok(
                ApiResponse.<List<Notification>>builder()
                        .success(true)
                        .message("Notifications fetched successfully")
                        .data(notifications)
                        .build()
        );
    }

}