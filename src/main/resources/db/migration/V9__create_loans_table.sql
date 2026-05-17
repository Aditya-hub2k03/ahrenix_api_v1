CREATE TABLE loans (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    loan_amount NUMERIC(19,4) NOT NULL,
    tenure_months INTEGER NOT NULL,
    interest_rate NUMERIC(5,2) NOT NULL,

    status VARCHAR(50) NOT NULL,

    remaining_amount NUMERIC(19,4) NOT NULL,

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_loan_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);