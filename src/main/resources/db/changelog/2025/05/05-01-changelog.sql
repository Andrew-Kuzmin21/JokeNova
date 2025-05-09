-- liquibase formatted sql

-- changeset andrew:1746433628786-1
CREATE SEQUENCE IF NOT EXISTS jokes_id_seq START WITH 1 INCREMENT BY 1;

-- changeset andrew:1746433628786-2
CREATE TABLE jokes
(
    id          BIGINT NOT NULL,
    text_joke   VARCHAR(2000),
    created_at  date,
    modified_at date,
    CONSTRAINT pk_jokes PRIMARY KEY (id)
);

