package com.yp.ahrenix.repository;

import com.yp.ahrenix.entities.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdempotencyKeyRepository
        extends JpaRepository<IdempotencyKey, Long> {

    Optional<IdempotencyKey>
    findByIdempotencyKey(String key);

}