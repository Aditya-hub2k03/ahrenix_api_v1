package com.yp.ahrenix.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.*;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic transactionTopic() {

        return TopicBuilder.name("transactions-created")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notificationTopic() {

        return TopicBuilder.name("notifications")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic auditTopic() {

        return TopicBuilder.name("audit-events")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic fraudAlertTopic() {

        return TopicBuilder.name("fraud-alerts")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic transactionDLQ() {

    return TopicBuilder
            .name("transactions-created-dlq")
            .partitions(3)
            .replicas(1)
            .build();
    }

}