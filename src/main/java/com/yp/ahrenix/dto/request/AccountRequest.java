package com.yp.ahrenix.dto.request;

import com.yp.ahrenix.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotNull(message = "Account type is required")
    private AccountType accountType;

    @NotNull(message = "Initial deposit is required")
    private BigDecimal initialDeposit;

}