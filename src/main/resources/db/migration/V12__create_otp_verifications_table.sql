CREATE TABLE otp_verifications (

    id BIGSERIAL PRIMARY KEY,
    version BIGINT,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    email VARCHAR(255) NOT NULL,
    otp VARCHAR(10) NOT NULL,

    expiry_time TIMESTAMP NOT NULL,

    verified BOOLEAN NOT NULL DEFAULT FALSE
);