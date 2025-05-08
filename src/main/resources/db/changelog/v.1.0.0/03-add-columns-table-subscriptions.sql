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

comment on column subscriptions.user_id is 'Связь с таблицей пользователя по идентификатору пользователя';
comment on column subscriptions.type is 'Наименование подписки';
comment on column subscriptions.price is 'Цена подписки';
comment on column subscriptions.subscription_termination is 'Период подписки';
comment on column subscriptions.expiration_date is 'Дата и время окончания подписки';