CREATE TABLE restaurante_usuario_responsavel
(
    id_restaurante BINARY(36) NOT NULL,
    id_usuario     BINARY(36) NOT NULL,
    CONSTRAINT pk_restaurante_usuario_responsavel PRIMARY KEY (id_restaurante, id_usuario)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE restaurante_usuario_responsavel
    ADD CONSTRAINT fk_resusures_on_restaurante FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE restaurante_usuario_responsavel
    ADD CONSTRAINT fk_resusures_on_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id);