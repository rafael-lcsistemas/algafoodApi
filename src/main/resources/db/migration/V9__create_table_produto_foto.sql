CREATE TABLE produto_foto
(
    produto_id         BINARY(16)   NOT NULL,
    nome_arquivo       VARCHAR(150) NOT NULL,
    descricao          VARCHAR(150) NULL,
    content_type       VARCHAR(100) NOT NULL,
    tamanho            INT          NOT NULL,
    datahora_alteracao datetime     NULL,
    CONSTRAINT pk_id_produto PRIMARY KEY (produto_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE produto_foto
    ADD CONSTRAINT fk_produto_foto_id_produto FOREIGN KEY (produto_id) REFERENCES produto (id);