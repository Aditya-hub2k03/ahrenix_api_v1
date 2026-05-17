package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.Transaction;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public Transaction getTransactionByReferenceId(
            String referenceId
    ) {

        return transactionRepository.findByReferenceId(referenceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Transaction not found"
                        )
                );
    }

}