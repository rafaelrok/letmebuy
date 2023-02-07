CREATE TABLE IF NOT EXISTS tb_user
(
    id          BIGINT NOT NULL,
    email       VARCHAR(255),
    password    VARCHAR(255),
    costumer_id BIGINT,
    CONSTRAINT pk_tb_user PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tb_user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_tb_user_role PRIMARY KEY (role_id, user_id)
);

ALTER TABLE tb_user
    ADD CONSTRAINT uc_tb_user_email UNIQUE (email);

ALTER TABLE tb_user
    ADD CONSTRAINT FK_TB_USER_ON_COSTUMER FOREIGN KEY (costumer_id) REFERENCES tb_costumer (id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_tbuserol_on_role FOREIGN KEY (role_id) REFERENCES tb_role (id);

ALTER TABLE tb_user_role
    ADD CONSTRAINT fk_tbuserol_on_user FOREIGN KEY (user_id) REFERENCES tb_user (id);