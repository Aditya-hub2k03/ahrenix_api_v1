package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getCurrentUser(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        return ResponseEntity.ok(
                ApiResponse.<User>builder()
                        .success(true)
                        .message("User fetched successfully")
                        .data(user)
                        .build()
        );
    }

}