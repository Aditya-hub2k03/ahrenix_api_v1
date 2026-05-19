package com.yp.ahrenix.mapper;

import com.yp.ahrenix.dto.response.NotificationResponse;
import com.yp.ahrenix.entities.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponse toResponse(Notification notification);

}