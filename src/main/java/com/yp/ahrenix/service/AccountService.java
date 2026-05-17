package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.exception.ResourceNotFoundException;
import com.yp.ahrenix.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

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

}