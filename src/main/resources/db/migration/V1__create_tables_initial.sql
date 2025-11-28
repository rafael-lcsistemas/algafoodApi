CREATE TABLE pais
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_pais PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE estado
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    uf                 VARCHAR(5)            NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_estado PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE cidade
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    id_estado          BIGINT                NULL,
    nome               VARCHAR(100)          NOT NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    ativo              BIT(1)                NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE cozinha
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    ativo              BIT(1)                NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_cozinha PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE forma_pagamento
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    ativo              BIT(1)                NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_formapagamento PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE grupo
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    ativo              BIT(1)                NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_grupo PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE permissao
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    descricao          VARCHAR(100)          NOT NULL,
    ativo              BIT(1)                NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_permissao PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE grupo_permissao
(
    id_grupo     BIGINT NOT NULL,
    id_permissao BIGINT NOT NULL
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE produto
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    nome           VARCHAR(150)          NOT NULL,
    descricao      VARCHAR(150)          NULL,
    preco          DECIMAL               NOT NULL,
    ativo          BIT(1)                NOT NULL,
    id_restaurante BIGINT                NULL,
    data_cadastro  timestamp             NOT NULL,
    data_alteracao timestamp             NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE restaurante
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    nome                 VARCHAR(100)          NOT NULL,
    taxa_frete           DECIMAL               NULL,
    ativo                BIT(1)                NULL,
    datahora_cadastro    timestamp             NOT NULL,
    datahora_alteracao   timestamp             NOT NULL,
    id_cozinha           BIGINT                NOT NULL,
    endereco_cep         VARCHAR(255)          NULL,
    endereco_logradouro  VARCHAR(255)          NULL,
    endereco_numero      VARCHAR(255)          NULL,
    endereco_complemento VARCHAR(255)          NULL,
    endereco_bairro      VARCHAR(255)          NULL,
    endereco_id_cidade   BIGINT                NULL,
    CONSTRAINT pk_restaurante PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE restaurante_forma_pagamento
(
    id_forma_pagamento BIGINT NOT NULL,
    id_restaurante     BIGINT NOT NULL
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE usuario
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    email              VARCHAR(100)          NULL,
    senha              VARCHAR(30)           NULL,
    ativo              BIT(1)                NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

CREATE TABLE usuario_grupo
(
    id_grupo   BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE cidade
    ADD CONSTRAINT FK_CIDADE_ON_ID_ESTADO FOREIGN KEY (id_estado) REFERENCES estado (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_gruper_on_grupo FOREIGN KEY (id_grupo) REFERENCES grupo (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_gruper_on_permissao FOREIGN KEY (id_permissao) REFERENCES permissao (id);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_ID_RESTAURANTE FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_ENDERECO_ID_CIDADE FOREIGN KEY (endereco_id_cidade) REFERENCES cidade (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_ID_COZINHA FOREIGN KEY (id_cozinha) REFERENCES cozinha (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_forma_pagamento FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_restaurante FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_grupo FOREIGN KEY (id_grupo) REFERENCES grupo (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id);