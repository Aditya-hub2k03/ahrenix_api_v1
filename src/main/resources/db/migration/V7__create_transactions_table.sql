CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    reference_id VARCHAR(255) NOT NULL UNIQUE,
    transaction_type VARCHAR(50) NOT NULL,
    transaction_status VARCHAR(50) NOT NULL,

    amount NUMERIC(19,4) NOT NULL,
    description VARCHAR(500),

    sender_account_id BIGINT,
    receiver_account_id BIGINT,

    CONSTRAINT fk_sender_account
        FOREIGN KEY (sender_account_id)
        REFERENCES accounts(id),

    CONSTRAINT fk_receiver_account
        FOREIGN KEY (receiver_account_id)
        REFERENCES accounts(id)
);

CREATE INDEX idx_transaction_reference
ON transactions(reference_id);