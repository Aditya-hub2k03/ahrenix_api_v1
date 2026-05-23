package com.yp.ahrenix.dto.response;

import com.yp.ahrenix.enums.LoanStatus;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {

    private Long id;

    private BigDecimal loanAmount;

    private Integer tenureMonths;

    private BigDecimal interestRate;

    private BigDecimal remainingAmount;

    private LoanStatus status;

    private Long userId;

    private String userName;

}