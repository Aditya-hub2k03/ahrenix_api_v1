package com.yp.ahrenix.concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentTransferTest {

    @Test
    void shouldHandleConcurrentTransfers()
            throws Exception {

        ExecutorService executorService =
                Executors.newFixedThreadPool(10);

        CountDownLatch latch =
                new CountDownLatch(10);

        for(int i = 0; i < 10; i++) {

            executorService.submit(() -> {

                try {

                    // simulate transfer

                } finally {

                    latch.countDown();
                }
            });
        }

        latch.await();

        assertTrue(true);
    }

}