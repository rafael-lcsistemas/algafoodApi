CREATE TABLE pedido
(
    id                    BIGINT AUTO_INCREMENT NOT NULL,
    total                 DECIMAL               NOT NULL,
    taxa_frete            DECIMAL               NOT NULL,
    valor_desconto        DECIMAL               NOT NULL,
    subtotal              DECIMAL               NOT NULL,
    id_usuario            BIGINT                NOT NULL,
    id_restaurante        BIGINT                NOT NULL,
    id_forma_pagamento     BIGINT                NOT NULL,
    status_pedido         VARCHAR(10)           NOT NULL,
    datahora_pedido       datetime              NOT NULL,
    datahora_confirmacao  datetime              NULL,
    datahora_cancelamento datetime              NULL,
    datahora_entrega      datetime              NULL,
    endereco_cep          VARCHAR(255)          NULL,
    endereco_logradouro   VARCHAR(255)          NULL,
    endereco_numero       VARCHAR(255)          NULL,
    endereco_complemento  VARCHAR(255)          NULL,
    endereco_bairro       VARCHAR(255)          NULL,
    endereco_id_cidade    BIGINT                NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE pedidodet
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    id_pedido      BIGINT                NULL,
    id_produto     BIGINT                NOT NULL,
    preco          DECIMAL               NOT NULL,
    quantidade     DECIMAL               NOT NULL,
    total          DECIMAL               NOT NULL,
    valor_desconto DECIMAL               NOT NULL,
    subtotal       DECIMAL               NOT NULL,
    observacao     VARCHAR(500)          NULL,
    CONSTRAINT pk_pedidodet PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_ENDERECO_ID_CIDADE FOREIGN KEY (endereco_id_cidade) REFERENCES cidade (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_ID_FORMAPAGAMENTO FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_ID_RESTAURANTE FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_ID_USUARIO FOREIGN KEY (id_usuario) REFERENCES usuario (id);

ALTER TABLE pedidodet
    ADD CONSTRAINT uc_pedidodet_id_produto UNIQUE (id_produto);

ALTER TABLE pedidodet
    ADD CONSTRAINT FK_PEDIDODET_ON_ID_PEDIDO FOREIGN KEY (id_pedido) REFERENCES pedido (id);

ALTER TABLE pedidodet
    ADD CONSTRAINT FK_PEDIDODET_ON_ID_PRODUTO FOREIGN KEY (id_produto) REFERENCES produto (id);