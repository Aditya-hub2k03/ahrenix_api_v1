package com.yp.ahrenix.dto.response;

import com.yp.ahrenix.enums.SupportTicketStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicketResponse {

    private Long id;

    private String subject;

    private String description;

    private SupportTicketStatus status;

    private LocalDateTime createdAt;

}