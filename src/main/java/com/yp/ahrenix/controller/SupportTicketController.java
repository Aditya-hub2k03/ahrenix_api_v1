package com.yp.ahrenix.controller;

import com.yp.ahrenix.dto.common.ApiResponse;
import com.yp.ahrenix.entities.SupportTicket;
import com.yp.ahrenix.entities.User;
import com.yp.ahrenix.security.UserPrincipal;
import com.yp.ahrenix.service.SupportTicketService;
import com.yp.ahrenix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class SupportTicketController {

    private final SupportTicketService supportTicketService;

    private final UserService userService;

    @GetMapping("/tickets/me")
    public ResponseEntity<ApiResponse<List<SupportTicket>>>
    getMyTickets(
            @AuthenticationPrincipal UserPrincipal principal
    ) {

        User user = userService.getUserByEmail(
                principal.getEmail()
        );

        List<SupportTicket> tickets =
                supportTicketService.getUserTickets(user);

        return ResponseEntity.ok(
                ApiResponse.<List<SupportTicket>>builder()
                        .success(true)
                        .message("Tickets fetched successfully")
                        .data(tickets)
                        .build()
        );
    }

}