package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.LoanResponse;
import com.yp.ahrenix.entities.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    LoanResponse toResponse(Loan loan);

}