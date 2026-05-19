package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.entities.Transaction;
import com.yp.ahrenix.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{referenceId}")
    public ResponseEntity<ApiResponse<Transaction>>
    getTransaction(
            @PathVariable String referenceId
    ) {

        Transaction transaction =
                transactionService.getTransactionByReferenceId(
                        referenceId
                );

        return ResponseEntity.ok(
                ApiResponse.<Transaction>builder()
                        .success(true)
                        .message("Transaction fetched successfully")
                        .data(transaction)
                        .build()
        );
    }

}