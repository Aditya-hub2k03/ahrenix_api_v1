CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    account_number VARCHAR(255) NOT NULL UNIQUE,
    account_type VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL,

    balance NUMERIC(19,4) NOT NULL,

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_account_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_account_number ON accounts(account_number);