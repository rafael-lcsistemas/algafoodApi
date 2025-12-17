CREATE TABLE categoria
(
    id                   BINARY(16)   NOT NULL,
    cod_interno          INT          NOT NULL,
    nome                 VARCHAR(100) NOT NULL,
    status               BIT(1)       NOT NULL,
    datahora_cadastro    timestamp    NOT NULL,
    datahora_atualizacao timestamp    NOT NULL,
    CONSTRAINT pk_categoria PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE categoria
    ADD CONSTRAINT uc_categoria_codinterno UNIQUE (cod_interno);

INSERT INTO categoria(id, cod_interno, nome, status, datahora_cadastro, datahora_atualizacao)
VALUES (UUID_TO_BIN('147d852c-c0b3-4add-bd0b-ae6255a8887b'), 1, 'PADRAO', true, UTC_TIMESTAMP, UTC_TIMESTAMP);

ALTER TABLE produto
    ADD COLUMN id_categoria BINARY(16) AFTER id_restaurante;

ALTER TABLE produto
    ADD CONSTRAINT fk_produto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria (id);

UPDATE produto
SET id_categoria = UUID_TO_BIN('147d852c-c0b3-4add-bd0b-ae6255a8887b')
WHERE id_categoria IS NULL;
