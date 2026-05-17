package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.SupportTicket;
import com.yp.ahrenix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    List<SupportTicket> findByUser(User user);

}