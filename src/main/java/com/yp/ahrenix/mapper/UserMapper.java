package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.UserResponse;
import com.yp.ahrenix.entities.Role;
import com.yp.ahrenix.entities.User;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles",
            expression = "java(mapRoles(user.getRoles()))")
    UserResponse toResponse(User user);

    default Set<String> mapRoles(Set<Role> roles) {

        return roles.stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
    }

}