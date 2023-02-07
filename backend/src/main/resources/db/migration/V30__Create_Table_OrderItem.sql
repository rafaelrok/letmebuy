CREATE TABLE IF NOT EXISTS tb_order_item
(
    discount   DOUBLE PRECISION,
    quantity   INTEGER,
    price      DOUBLE PRECISION,
    order_id   INTEGER NOT NULL,
    product_id BIGINT  NOT NULL,
    CONSTRAINT pk_tb_order_item PRIMARY KEY (order_id, product_id)
);

ALTER TABLE tb_order_item
    ADD CONSTRAINT FK_TB_ORDER_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES tb_order (id);

ALTER TABLE tb_order_item
    ADD CONSTRAINT FK_TB_ORDER_ITEM_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);