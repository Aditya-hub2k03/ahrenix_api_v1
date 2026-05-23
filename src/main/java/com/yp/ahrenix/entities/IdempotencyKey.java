package com.yp.ahrenix.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "idempotency_keys",
        indexes = {
                @Index(
                        name = "idx_idempotency_key",
                        columnList = "idempotency_key"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdempotencyKey extends BaseEntity {

    @Column(
            name = "idempotency_key",
            nullable = false,
            unique = true
    )
    private String idempotencyKey;

    @Column(nullable = false)
    private String endpoint;

    @Column(nullable = false)
    private Boolean processed;

}