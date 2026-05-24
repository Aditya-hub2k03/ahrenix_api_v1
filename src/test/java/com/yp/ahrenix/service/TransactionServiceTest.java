package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.TransactionRequest;
import com.yp.ahrenix.entities.*;
import com.yp.ahrenix.enums.*;
import com.yp.ahrenix.exception.InsufficientBalanceException;
import com.yp.ahrenix.repository.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenBalanceInsufficient() {

        User user = User.builder().build();

        Account sender =
                Account.builder()
                        .balance(BigDecimal.valueOf(100))
                        .build();

        TransactionRequest request =
                TransactionRequest.builder()
                        .receiverAccountNumber("AHX123")
                        .amount(BigDecimal.valueOf(1000))
                        .build();

        when(accountRepository.findByUser(user))
                .thenReturn(List.of(sender));

        assertThrows(
                InsufficientBalanceException.class,
                () -> transactionService.transferMoney(
                        user,
                        request,
                        "127.0.0.1"
                )
        );
    }

}