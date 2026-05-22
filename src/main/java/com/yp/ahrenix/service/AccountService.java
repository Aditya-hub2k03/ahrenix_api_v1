package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.AccountRequest;
import com.yp.ahrenix.dto.response.AccountResponse;
import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.enums.AccountStatus;
import com.yp.ahrenix.mapper.AccountMapper;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.repository.AccountRepository;
import com.yp.ahrenix.repository.UserRepository;
import com.yp.ahrenix.util.AccountNumberGenerator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final UserRepository userRepository;
    private final AuditService auditService;

    public Account getAccountByAccountNumber(
            String accountNumber
    ) {

        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Account not found"
                        )
                );
    }

    public List<Account> getAccountsByUser(User user) {

        return accountRepository.findByUser(user);
    }

    public AccountResponse mapToResponse(
        Account account
) {

    return accountMapper.toResponse(account);
}

    @Transactional
public AccountResponse createAccount(
        User user,
        AccountRequest request,
        String ipAddress
) {

    Account account = Account.builder()
            .accountNumber(
                    AccountNumberGenerator.generateAccountNumber()
            )
            .accountType(request.getAccountType())
            .status(AccountStatus.ACTIVE)
            .balance(request.getInitialDeposit())
            .user(user)
            .build();

    Account savedAccount =
            accountRepository.save(account);

    auditService.logAction(
            "CREATE_ACCOUNT",
            "ACCOUNT",
            user.getEmail(),
            "Created account: " +
                    savedAccount.getAccountNumber(),
            ipAddress
    );

    return accountMapper.toResponse(savedAccount);
}

}