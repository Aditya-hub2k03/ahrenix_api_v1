package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.LoanResponse;
import com.yp.ahrenix.entities.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userName")
    LoanResponse toResponse(Loan loan);

}