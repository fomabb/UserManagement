ALTER TABLE subscriptions
    DROP COLUMN name;

ALTER TABLE subscriptions
    ADD COLUMN type VARCHAR(50) NOT NULL DEFAULT '';

ALTER TABLE subscriptions
    ADD COLUMN price DECIMAL DEFAULT 0.0;

ALTER TABLE subscriptions
    ADD COLUMN subscription_termination VARCHAR(50);

ALTER TABLE subscriptions
    ADD COLUMN expiration_date TIMESTAMP;
