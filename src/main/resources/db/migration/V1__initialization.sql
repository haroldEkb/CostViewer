-- *new password for user postgres
-- ALTER USER postgres PASSWORD 'admin';

--DROP SCHEMA IF EXISTS costviewer CASCADE;
--CREATE SCHEMA costviewer;
--
--SET search_path TO costviewer;

DROP TABLE IF EXISTS records;
CREATE TABLE records
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    record_date     DATE,
    value           real,
    commentary      TEXT
);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
--    first_name VARCHAR(50),
--    last_name  VARCHAR(50),
    nickname  VARCHAR(50) UNIQUE
--    password   VARCHAR(80),
--    email      VARCHAR(50) UNIQUE,
--    email_verified BOOLEAN,
--    status     VARCHAR(50)
);
--INSERT INTO users (first_name, last_name, nick_name, password, email, email_verified, status)
--VALUES ('Admin', 'Admin', 'admin', '100', 'admin@gmail.com', false, 'ACTIVE');

INSERT INTO users (nickname)
VALUES ('Admin');
