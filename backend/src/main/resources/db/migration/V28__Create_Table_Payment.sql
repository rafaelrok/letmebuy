CREATE TABLE IF NOT EXISTS tb_payment
(
    order_id          INTEGER NOT NULL,
    type_payment      INTEGER,
    payment_method_id BIGINT,
    CONSTRAINT pk_tb_payment PRIMARY KEY (order_id)
);

ALTER TABLE tb_payment
    ADD CONSTRAINT FK_TB_PAYMENT_ON_ORDER FOREIGN KEY (order_id) REFERENCES tb_order (id);

ALTER TABLE tb_payment
    ADD CONSTRAINT FK_TB_PAYMENT_ON_PAYMENT_METHOD FOREIGN KEY (payment_method_id) REFERENCES tb_payment_method (id);