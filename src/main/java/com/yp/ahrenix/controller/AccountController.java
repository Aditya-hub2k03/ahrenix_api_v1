package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.AccountService;
import com.yp.ahrenix.service.UserService;
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
    public ResponseEntity<ApiResponse<List<Account>>> getMyAccounts(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        List<Account> accounts =
                accountService.getAccountsByUser(user);

        return ResponseEntity.ok(
                ApiResponse.<List<Account>>builder()
                        .success(true)
                        .message("Accounts fetched successfully")
                        .data(accounts)
                        .build()
        );
    }

}