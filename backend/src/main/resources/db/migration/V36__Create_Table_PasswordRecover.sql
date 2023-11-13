CREATE TABLE IF NOT EXISTS tb_password_recover
(
    id            BIGINT NOT NULL,
    token         VARCHAR(255),
    email         VARCHAR(255),
    expiration    TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tb_password_recover PRIMARY KEY (id)
);