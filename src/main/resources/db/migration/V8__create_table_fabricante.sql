CREATE TABLE fabricante
(
    id                   BINARY(16)   NOT NULL,
    cod_interno          INT          NOT NULL,
    nome                 VARCHAR(100) NOT NULL,
    status               BIT(1)       NOT NULL,
    datahora_cadastro    timestamp    NOT NULL,
    datahora_atualizacao timestamp    NOT NULL,
    CONSTRAINT pk_fabricante PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE fabricante
    ADD CONSTRAINT uc_fabricante_codinterno UNIQUE (cod_interno);

INSERT INTO fabricante(id, cod_interno, nome, status, datahora_cadastro, datahora_atualizacao)
VALUES (UUID_TO_BIN('19724b99-cb6a-4ef2-84c8-f577e5e445ca'), 1, 'PADRAO', true, UTC_TIMESTAMP, UTC_TIMESTAMP);

ALTER TABLE produto
    ADD COLUMN id_fabricante BINARY(16) AFTER id_categoria;

ALTER TABLE produto
    ADD CONSTRAINT fk_produto_fabricante FOREIGN KEY (id_fabricante) REFERENCES fabricante (id);

UPDATE produto
SET id_fabricante = UUID_TO_BIN('19724b99-cb6a-4ef2-84c8-f577e5e445ca')
WHERE id_fabricante IS NULL;
