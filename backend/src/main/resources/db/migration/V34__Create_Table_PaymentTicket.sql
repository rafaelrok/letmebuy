CREATE TABLE IF NOT EXISTS tb_payment_ticket
(
    due_date     TIMESTAMP WITHOUT TIME ZONE,
    payment_date TIMESTAMP WITHOUT TIME ZONE,
    order_id     INTEGER NOT NULL,
    CONSTRAINT pk_tb_payment_ticket PRIMARY KEY (order_id)
);

ALTER TABLE tb_payment_ticket
    ADD CONSTRAINT FK_TB_PAYMENT_TICKET_ON_ORDER FOREIGN KEY (order_id) REFERENCES tb_order (id);