package com.yp.ahrenix.service;

import com.yp.ahrenix.dto.request.AccountRequest;
import com.yp.ahrenix.dto.response.AccountResponse;
import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.mapper.AccountMapper;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.repository.AccountRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

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

    public AccountResponse createAccount(User user, AccountRequest request, HttpServletRequest servletRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAccount'");
    }

}