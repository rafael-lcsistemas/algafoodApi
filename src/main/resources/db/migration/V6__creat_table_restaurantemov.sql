CREATE TABLE restaurante_mov
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    id_restaurante     BIGINT                NOT NULL,
    id_usuario         BIGINT                NOT NULL,
    datahora_movimento datetime              NULL,
    tipo_movimento     VARCHAR(10)           NULL,
    valor_movimento    DECIMAL               NULL,
    observacoes        VARCHAR(500)          NULL,
    CONSTRAINT pk_restaurantemov PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE restaurante_mov
    ADD CONSTRAINT FK_RESTAURANTEMOV_ON_ID_RESTAURANTE FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE restaurante_mov
    ADD CONSTRAINT FK_RESTAURANTEMOV_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);