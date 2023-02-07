CREATE TABLE IF NOT EXISTS tb_order
(
    id                  INTEGER NOT NULL,
    date                DATE NOT NULL,
    status              INTEGER,
    user_id             BIGINT,
    address_delivery_id INTEGER,
    amount              DOUBLE PRECISION,
    CONSTRAINT pk_tb_order PRIMARY KEY (id)
);

ALTER TABLE tb_order
    ADD CONSTRAINT FK_TB_ORDER_ON_ADDRESS_DELIVERY FOREIGN KEY (address_delivery_id) REFERENCES tb_address (id);

ALTER TABLE tb_order
    ADD CONSTRAINT FK_TB_ORDER_ON_USER FOREIGN KEY (user_id) REFERENCES tb_user (id);