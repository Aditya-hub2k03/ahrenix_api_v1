package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.UserResponse;
import com.yp.ahrenix.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

}