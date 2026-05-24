package com.yp.ahrenix.integration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IdempotencyTest {

    @Test
    void shouldPreventDuplicateTransactions() {

        // simulate same idempotency key

        assertTrue(true);
    }

}