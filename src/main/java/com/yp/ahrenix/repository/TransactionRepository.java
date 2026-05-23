package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.Account;
import com.yp.ahrenix.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByReferenceId(String referenceId);

    List<Transaction> findBySenderAccount(Account senderAccount);

    List<Transaction> findByReceiverAccount(Account receiverAccount);

    Page<Transaction> findBySenderAccountOrReceiverAccount(
        Account senderAccount,
        Account receiverAccount,
        Pageable pageable
);

}