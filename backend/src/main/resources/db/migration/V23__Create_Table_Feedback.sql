CREATE TABLE IF NOT EXISTS tb_feedback
(
    id         BIGINT NOT NULL,
    text       TEXT,
    product_id BIGINT,
    user_id    BIGINT,
    CONSTRAINT pk_tb_feedback PRIMARY KEY (id)
);

ALTER TABLE tb_feedback
    ADD CONSTRAINT FK_TB_FEEDBACK_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES tb_product (id);

ALTER TABLE tb_feedback
    ADD CONSTRAINT FK_TB_FEEDBACK_ON_USER FOREIGN KEY (user_id) REFERENCES tb_user (id);