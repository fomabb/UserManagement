ALTER TABLE subscription_stats
    ADD COLUMN price DECIMAL DEFAULT 0.0;

comment on column subscription_stats.price is 'Цена подписки';
