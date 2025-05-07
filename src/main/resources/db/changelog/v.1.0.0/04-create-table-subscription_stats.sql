CREATE TABLE subscription_stats
(
    type       VARCHAR(50) NOT NULL PRIMARY KEY,
    popularity INT         NOT NULL DEFAULT 0
);