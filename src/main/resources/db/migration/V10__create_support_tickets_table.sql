CREATE TABLE support_tickets (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    subject VARCHAR(255) NOT NULL,
    description VARCHAR(3000) NOT NULL,

    status VARCHAR(50) NOT NULL,

    user_id BIGINT NOT NULL,

    CONSTRAINT fk_support_ticket_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);