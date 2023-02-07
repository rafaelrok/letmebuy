CREATE TABLE IF NOT EXISTS tb_payment_card
(
    number_of_installments INTEGER,
    order_id               INTEGER NOT NULL,
    CONSTRAINT pk_tb_payment_card PRIMARY KEY (order_id)
);

ALTER TABLE tb_payment_card
    ADD CONSTRAINT FK_TB_PAYMENT_CARD_ON_ORDER FOREIGN KEY (order_id) REFERENCES tb_order (id);