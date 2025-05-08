CREATE TABLE subscriptions
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    create_at   TIMESTAMP,
    user_id     BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

comment on table subscriptions is 'Таблица для хранения данных о подписках пользователя';
comment on column subscriptions.id is 'Идентификатор подписки';
comment on column subscriptions.description is 'Описание подписки';
comment on column subscriptions.create_at is 'Дата и время создания подписки';