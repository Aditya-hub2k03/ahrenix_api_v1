CREATE TABLE idempotency_keys (

    id BIGSERIAL PRIMARY KEY,
    version BIGINT,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    idempotency_key VARCHAR(255)
        NOT NULL UNIQUE,

    endpoint VARCHAR(255)
        NOT NULL,

    processed BOOLEAN NOT NULL
);