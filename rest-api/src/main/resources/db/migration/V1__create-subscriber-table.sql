CREATE TABLE member
(
    id                  BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    login               VARCHAR(64) UNIQUE NOT NULL,
    password            VARCHAR(64) NOT NULL,
    telegram_chat_id    BIGINT UNIQUE
);