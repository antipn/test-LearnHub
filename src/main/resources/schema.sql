DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100 INCREMENT BY 1;


CREATE TABLE users
(
    id          BIGINT DEFAULT NEXT VALUE FOR global_seq not null primary key,
    name        VARCHAR(255)     NOT NULL,
    email       VARCHAR(255)     NOT NULL,
    phone       VARCHAR(255)     NOT NULL,
    secret      VARCHAR(255)     NOT NULL

);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);
CREATE UNIQUE INDEX users_unique_name_idx ON users (name);