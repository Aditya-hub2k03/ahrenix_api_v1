package com.yp.ahrenix.service;

import com.yp.ahrenix.entities.AuditLog;
import com.yp.ahrenix.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void logAction(
            String action,
            String entityName,
            String username,
            String details,
            String ipAddress
    ) {

        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .entityName(entityName)
                .username(username)
                .details(details)
                .ipAddress(ipAddress)
                .build();

        auditLogRepository.save(auditLog);
    }

}