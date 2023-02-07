CREATE TABLE IF NOT EXISTS tb_address
(
    id           INTEGER NOT NULL,
    street       VARCHAR(255),
    number       VARCHAR(255),
    complement   VARCHAR(255),
    neighborhood VARCHAR(255),
    zipcode      VARCHAR(255),
    city_id      INTEGER,
    CONSTRAINT pk_tb_address PRIMARY KEY (id)
);

ALTER TABLE tb_address
    ADD CONSTRAINT FK_TB_ADDRESS_ON_CITY FOREIGN KEY (city_id) REFERENCES tb_city (id);

