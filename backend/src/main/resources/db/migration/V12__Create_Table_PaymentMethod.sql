CREATE TABLE IF NOT EXISTS tb_payment_method
(
    id          BIGINT NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_tb_payment_method PRIMARY KEY (id)
);