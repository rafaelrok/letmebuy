CREATE TABLE IF NOT EXISTS tb_costumer
(
    id          BIGINT NOT NULL,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    cpf_ou_cnpj VARCHAR(255),
    type        INTEGER,
    CONSTRAINT pk_tb_costumer PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_costumer_address
(
    costumer_id BIGINT  NOT NULL,
    address_id  INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tb_phone
(
    costumer_id BIGINT NOT NULL,
    phones      VARCHAR(255)
);

ALTER TABLE tb_costumer_address
    ADD CONSTRAINT uc_tb_costumer_address_address UNIQUE (address_id);

ALTER TABLE tb_phone
    ADD CONSTRAINT fk_tb_phone_on_costumer FOREIGN KEY (costumer_id) REFERENCES tb_costumer (id);

ALTER TABLE tb_costumer_address
    ADD CONSTRAINT fk_tbcosadd_on_address FOREIGN KEY (address_id) REFERENCES tb_address (id);

ALTER TABLE tb_costumer_address
    ADD CONSTRAINT fk_tbcosadd_on_costumer FOREIGN KEY (costumer_id) REFERENCES tb_costumer (id);