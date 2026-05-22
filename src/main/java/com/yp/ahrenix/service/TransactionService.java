package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.TransactionRequest;
import com.yp.ahrenix.dto.response.TransactionResponse;
import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.Transaction;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.enums.TransactionStatus;
import com.yp.ahrenix.enums.TransactionType;
import com.yp.ahrenix.exception.InsufficientBalanceException;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.mapper.TransactionMapper;
import com.yp.ahrenix.repository.AccountRepository;
import com.yp.ahrenix.repository.TransactionRepository;
import com.yp.ahrenix.util.TransactionReferenceGenerator;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final KafkaProducerService kafkaProducerService;
    private final AuditService auditService;

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

    @Transactional
public TransactionResponse transferMoney(
        User user,
        TransactionRequest request,
        String ipAddress
) {

    Account senderAccount =
            accountRepository.findByUser(user)
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Sender account not found"
                            )
                    );

    Account receiverAccount =
            accountRepository.findByAccountNumber(
                    request.getReceiverAccountNumber()
            )
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Receiver account not found"
                            )
                    );

    if (senderAccount.getBalance()
            .compareTo(request.getAmount()) < 0) {

        throw new InsufficientBalanceException(
                "Insufficient balance"
        );
    }

    senderAccount.setBalance(
            senderAccount.getBalance()
                    .subtract(request.getAmount())
    );

    receiverAccount.setBalance(
            receiverAccount.getBalance()
                    .add(request.getAmount())
    );

    accountRepository.save(senderAccount);
    accountRepository.save(receiverAccount);

    Transaction transaction = Transaction.builder()
            .referenceId(
                    TransactionReferenceGenerator
                            .generateReference()
            )
            .transactionType(TransactionType.TRANSFER)
            .transactionStatus(TransactionStatus.SUCCESS)
            .amount(request.getAmount())
            .description(request.getDescription())
            .senderAccount(senderAccount)
            .receiverAccount(receiverAccount)
            .build();

    Transaction savedTransaction =
            transactionRepository.save(transaction);

    kafkaProducerService.sendMessage(
            "transactions-created",
            "Transaction Created: " +
                    savedTransaction.getReferenceId()
    );

    auditService.logAction(
            "TRANSFER_MONEY",
            "TRANSACTION",
            user.getEmail(),
            "Transferred " +
                    request.getAmount(),
            ipAddress
    );

    return transactionMapper.toResponse(savedTransaction);
}

}