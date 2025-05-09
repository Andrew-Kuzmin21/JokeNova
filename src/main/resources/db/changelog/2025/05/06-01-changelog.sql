-- liquibase formatted sql

-- changeset andrew:1746548331833-1
CREATE SEQUENCE IF NOT EXISTS joke_call_id_seq START WITH 1 INCREMENT BY 1;

-- changeset andrew:1746548331833-2
CREATE TABLE joke_calls
(
    id        BIGINT NOT NULL,
    call_time TIMESTAMP WITHOUT TIME ZONE,
    joke_id   BIGINT,
    user_id   BIGINT,
    CONSTRAINT pk_joke_calls PRIMARY KEY (id)
);

-- changeset andrew:1746548331833-3
CREATE TABLE users
(
    id       BIGINT NOT NULL,
    username VARCHAR(255),
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset andrew:1746548331833-4
ALTER TABLE joke_calls
    ADD CONSTRAINT FK_JOKE_CALLS_ON_JOKE FOREIGN KEY (joke_id) REFERENCES jokes (id);

-- changeset andrew:1746548331833-5
ALTER TABLE joke_calls
    ADD CONSTRAINT FK_JOKE_CALLS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

