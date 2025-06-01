-- liquibase formatted sql

-- changeset andrew:1746782414692-1
CREATE SEQUENCE IF NOT EXISTS joke_call_id_seq START WITH 1 INCREMENT BY 1;

-- changeset andrew:1746782414692-2
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;

-- changeset andrew:1746782414692-3
CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;

-- changeset andrew:1746782414692-5
CREATE TABLE roles
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT NOT NULL,
    login VARCHAR(255),
    password VARCHAR(255),
    role_id  BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- ALTER TABLE users DROP COLUMN username;
-- ALTER TABLE users ADD COLUMN password VARCHAR(255);
-- ALTER TABLE users ADD COLUMN login VARCHAR(255);
-- ALTER TABLE users ADD COLUMN role_id  BIGINT CONSTRAINT pk_users PRIMARY KEY (id);


-- changeset andrew:1746782414692-9
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

