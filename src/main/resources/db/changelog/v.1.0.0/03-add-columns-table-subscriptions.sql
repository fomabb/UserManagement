ALTER TABLE subscriptions
    DROP COLUMN name;

ALTER TABLE subscriptions
    ADD COLUMN type VARCHAR(50) NOT NULL UNIQUE DEFAULT '';

ALTER TABLE subscriptions
    ADD COLUMN popularity INT DEFAULT 0;

ALTER TABLE subscriptions
    ADD COLUMN price DECIMAL DEFAULT 0.0
