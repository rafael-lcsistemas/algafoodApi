CREATE TABLE restaurante_mov
(
    id                 BINARY(36)   NOT NULL,
    cod_interno        INT          NOT NULL,
    id_restaurante     BINARY(36)   NULL,
    id_usuario         BINARY(36)   NULL,
    datahora_movimento datetime     NOT NULL,
    tipo_movimento     VARCHAR(10)  NOT NULL,
    valor_movimento    DECIMAL      NOT NULL,
    observacoes        VARCHAR(500) NULL,
    CONSTRAINT pk_restaurantemov PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE restaurante_mov
    ADD CONSTRAINT uc_restaurantemov_codinterno UNIQUE (cod_interno);

ALTER TABLE restaurante_mov
    ADD CONSTRAINT FK_RESTAURANTEMOV_ON_ID_RESTAURANTE FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE restaurante_mov
    ADD CONSTRAINT FK_RESTAURANTEMOV_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);