package com.yp.ahrenix.dto.response;

import com.yp.ahrenix.enums.AccountStatus;
import com.yp.ahrenix.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id;
    
    private Long userId;

    private String userName;

    private String accountNumber;

    private AccountType accountType;

    private AccountStatus status;

    private BigDecimal balance;

}