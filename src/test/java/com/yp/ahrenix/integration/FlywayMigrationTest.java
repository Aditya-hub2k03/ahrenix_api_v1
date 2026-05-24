package com.yp.ahrenix.integration;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FlywayMigrationTest {

    @Autowired
    private Flyway flyway;

    @Test
    void shouldRunFlywayMigrations() {

        assertTrue(
                flyway.info().applied().length > 0
        );
    }

}