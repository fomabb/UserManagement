CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255)        NOT NULL,
    last_name  VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    create_at  TIMESTAMP,
    update_at  TIMESTAMP
);

comment on table users is 'Таблица для хранения данных о пользователях';
comment on column users.id is 'Идентификатор пользователя';
comment on column users.first_name is 'Имя пользователя';
comment on column users.last_name is 'Фамилия пользователя';
comment on column users.email is 'Электронная почта пользователя';
comment on column users.password is 'Пароль пользователя';
comment on column users.create_at is 'Дата и время создания пользователя';
comment on column users.update_at is 'Дата и время обновления пользователя';