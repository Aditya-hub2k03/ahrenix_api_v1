package com.yp.ahrenix.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NotNull(message = "Loan amount is required")
    private BigDecimal loanAmount;

    @NotNull(message = "Tenure is required")
    private Integer tenureMonths;

}