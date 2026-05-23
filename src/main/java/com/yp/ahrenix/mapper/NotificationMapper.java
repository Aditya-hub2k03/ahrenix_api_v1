package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.NotificationResponse;
import com.yp.ahrenix.entities.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "userName")
    NotificationResponse toResponse(
            Notification notification
    );

}