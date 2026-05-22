package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.dto.response.LoanResponse;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.mapper.LoanMapper;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.LoanService;
import com.yp.ahrenix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    private final UserService userService;

    private final LoanMapper loanMapper;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<List<LoanResponse>>>
    getMyLoans(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        List<LoanResponse> loans =
                loanService.getUserLoans(user)
                        .stream()
                        .map(loanMapper::toResponse)
                        .toList();

        return ResponseEntity.ok(
                ApiResponse.<List<LoanResponse>>builder()
                        .success(true)
                        .message("Loans fetched successfully")
                        .data(loans)
                        .build()
        );
    }

}