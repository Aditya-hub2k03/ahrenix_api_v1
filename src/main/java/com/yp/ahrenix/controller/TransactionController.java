package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.dto.request.TransactionRequest;
import com.yp.ahrenix.dto.response.TransactionResponse;
import com.yp.ahrenix.entities.Transaction;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.mapper.TransactionMapper;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.TransactionService;
import com.yp.ahrenix.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    private final UserService userService;

    private final TransactionMapper transactionMapper;

    @GetMapping("/{referenceId}")
    public ResponseEntity<ApiResponse<TransactionResponse>>
    getTransaction(
            @PathVariable String referenceId
    ) {

        Transaction transaction =
                transactionService.getTransactionByReferenceId(
                        referenceId
                );

        TransactionResponse response =
                transactionMapper.toResponse(transaction);

        return ResponseEntity.ok(
                ApiResponse.<TransactionResponse>builder()
                        .success(true)
                        .message("Transaction fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/transfer")
    public ResponseEntity<ApiResponse<TransactionResponse>>
    transferMoney(
            @AuthenticationPrincipal UserPrincipal principal,
            @Valid @RequestBody TransactionRequest request,
            HttpServletRequest servletRequest
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        TransactionResponse response =
                transactionService.transferMoney(
                        user,
                        request,
                        servletRequest.getRemoteAddr()
                );

        return ResponseEntity.ok(
                ApiResponse.<TransactionResponse>builder()
                        .success(true)
                        .message("Transaction successful")
                        .data(response)
                        .build()
        );
    }

}