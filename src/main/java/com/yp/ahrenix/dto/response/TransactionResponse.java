package com.yp.ahrenix.dto.response;

import com.yp.ahrenix.enums.TransactionStatus;
import com.yp.ahrenix.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private Long id;

    private String referenceId;

    private TransactionType transactionType;

    private TransactionStatus transactionStatus;

    private BigDecimal amount;

    private String description;

    private String senderAccount;

    private String receiverAccount;

    private LocalDateTime createdAt;

    private String senderName;
    
    private String receiverName;

}