CREATE TABLE IF NOT EXISTS tb_category
(
    id         BIGINT,
    name       VARCHAR(255),
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tb_category PRIMARY KEY (id)
);