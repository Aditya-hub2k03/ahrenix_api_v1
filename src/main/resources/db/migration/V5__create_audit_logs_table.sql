CREATE TABLE audit_logs (
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    action VARCHAR(255) NOT NULL,
    entity_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    details VARCHAR(1000),
    ip_address VARCHAR(255) NOT NULL
);