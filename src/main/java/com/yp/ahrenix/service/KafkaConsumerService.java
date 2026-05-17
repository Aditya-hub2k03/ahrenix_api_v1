package com.yp.ahrenix.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(
            topics = "transactions-created",
            groupId = "ahrenix-group"
    )
    public void consumeTransactionEvent(
            String message
    ) {

        log.info("Consumed Kafka Message: {}", message);
    }

}