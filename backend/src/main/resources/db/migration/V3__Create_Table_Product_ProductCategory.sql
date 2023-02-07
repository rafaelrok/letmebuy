CREATE TABLE IF NOT EXISTS tb_product
(
    id          BIGINT NOT NULL,
    name        VARCHAR(255),
    description TEXT,
    price       DOUBLE PRECISION,
    img_url     VARCHAR(255),
    date        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tb_product PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_product_category
(
    product_id  BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    CONSTRAINT pk_tb_product_category PRIMARY KEY (product_id, category_id)
);

ALTER TABLE tb_product_category
    ADD CONSTRAINT fk_tbprocat_on_product FOREIGN KEY (product_id) REFERENCES tb_product (id);

ALTER TABLE tb_product_category
    ADD CONSTRAINT fk_tbprocat_on_category FOREIGN KEY (category_id) REFERENCES tb_category (id);