package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.TransactionResponse;
import com.yp.ahrenix.entities.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(
            source = "senderAccount.accountNumber",
            target = "senderAccount"
    )
    @Mapping(
            source = "receiverAccount.accountNumber",
            target = "receiverAccount"
    )
    @Mapping(
            source = "senderAccount.user.fullName",
            target = "senderName"
    )
    @Mapping(
            source = "receiverAccount.user.fullName",
            target = "receiverName"
    )
    TransactionResponse toResponse(
            Transaction transaction
    );

}