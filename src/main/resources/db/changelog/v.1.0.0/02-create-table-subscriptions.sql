CREATE TABLE subscriptions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    create_at   TIMESTAMP,
    update_at   TIMESTAMP,
    user_id     BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);