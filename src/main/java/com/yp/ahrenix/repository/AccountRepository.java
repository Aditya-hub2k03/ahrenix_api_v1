package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findByUser(User user);

}