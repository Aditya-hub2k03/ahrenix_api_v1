package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.dto.request.AccountRequest;
import com.yp.ahrenix.dto.response.AccountResponse;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.AccountService;
import com.yp.ahrenix.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<AccountResponse>>>
    getMyAccounts(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        List<AccountResponse> accounts =
                accountService.getAccountsByUser(user)
                        .stream()
                        .map(accountService::mapToResponse)
                        .toList();

        return ResponseEntity.ok(
                ApiResponse.<List<AccountResponse>>builder()
                        .success(true)
                        .message("Accounts fetched successfully")
                        .data(accounts)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AccountResponse>>
    createAccount(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody AccountRequest request,
            HttpServletRequest servletRequest
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        AccountResponse response =
                accountService.createAccount(
                        user,
                        request,
                        servletRequest
                );

        return ResponseEntity.ok(
                ApiResponse.<AccountResponse>builder()
                        .success(true)
                        .message("Account created successfully")
                        .data(response)
                        .build()
        );
    }

}