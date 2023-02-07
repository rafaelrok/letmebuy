CREATE TABLE IF NOT EXISTS tb_role
(
    id        BIGINT NOT NULL,
    authority VARCHAR(255),
    CONSTRAINT pk_tb_role PRIMARY KEY (id)
);