package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.SupportTicket;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.repository.SupportTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupportTicketService {

    private final SupportTicketRepository supportTicketRepository;

    public List<SupportTicket> getUserTickets(
            User user
    ) {

        return supportTicketRepository.findByUser(user);
    }

}