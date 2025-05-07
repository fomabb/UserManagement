CREATE TABLE subscription_stats
(
    type       VARCHAR(50) NOT NULL PRIMARY KEY,
    popularity INT         NOT NULL DEFAULT 0
);

comment on table subscription_stats is 'Таблица для хранения данных о имеющихся подписках';
comment on column subscription_stats.type is 'Название подписки';
comment on column subscription_stats.popularity is 'Рейтинг подписки';