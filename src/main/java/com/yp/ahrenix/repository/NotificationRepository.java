package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.Notification;
import com.yp.ahrenix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);

}