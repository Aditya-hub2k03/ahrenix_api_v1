package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.TransactionResponse;
import com.yp.ahrenix.entities.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionResponse toResponse(Transaction transaction);

}