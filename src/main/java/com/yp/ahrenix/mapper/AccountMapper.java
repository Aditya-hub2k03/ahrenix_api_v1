package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.AccountResponse;
import com.yp.ahrenix.entities.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse toResponse(Account account);

}