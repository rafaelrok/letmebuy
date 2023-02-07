CREATE TABLE IF NOT EXISTS tb_city
(
    id       INTEGER NOT NULL,
    name     VARCHAR(255),
    state_id INTEGER,
    CONSTRAINT pk_tb_city PRIMARY KEY (id)
);

ALTER TABLE tb_city
    ADD CONSTRAINT FK_TB_CITY_ON_STATE FOREIGN KEY (state_id) REFERENCES tb_state (id);