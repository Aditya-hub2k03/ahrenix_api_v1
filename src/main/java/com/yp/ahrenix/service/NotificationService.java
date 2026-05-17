package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.Notification;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public List<Notification> getUserNotifications(
            User user
    ) {

        return notificationRepository.findByUser(user);
    }

}