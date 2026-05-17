package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.Loan;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public List<Loan> getUserLoans(User user) {

        return loanRepository.findByUser(user);
    }

}