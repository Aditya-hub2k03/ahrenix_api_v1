package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.UserResponse;
import com.yp.ahrenix.entities.Role;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.enums.RoleType;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    default RoleType map(Role role) {
        return role == null ? null : role.getName();
    }

}