package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.AccountResponse;
import com.yp.ahrenix.entities.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userName")
    AccountResponse toResponse(Account account);

}