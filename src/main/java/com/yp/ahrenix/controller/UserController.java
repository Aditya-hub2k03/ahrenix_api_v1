package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.dto.response.UserResponse;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.mapper.UserMapper;
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
    private final UserMapper userMapper;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getCurrentUser(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );
        UserResponse response = userMapper.toResponse(user);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message("User fetched successfully")
                        .data(response)
                        .build()
        );
    }

}