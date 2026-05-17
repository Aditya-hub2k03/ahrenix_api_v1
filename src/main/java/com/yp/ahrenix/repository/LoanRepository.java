package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.Loan;
import com.yp.ahrenix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUser(User user);

}