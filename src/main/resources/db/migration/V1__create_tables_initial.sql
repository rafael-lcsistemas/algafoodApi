CREATE TABLE pais
(
    id                 BIGINT       NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    datahora_cadastro  timestamp    NOT NULL,
    datahora_alteracao timestamp    NOT NULL,
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
    id_estado          BIGINT                NOT NULL,
    nome               VARCHAR(100)          NOT NULL,
    datahora_cadastro  datetime              NULL,
    datahora_alteracao datetime              NULL,
    status             BIT(1)                NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE cidade
    ADD CONSTRAINT FK_CIDADE_ON_ID_ESTADO FOREIGN KEY (id_estado) REFERENCES estado (id);

CREATE TABLE cozinha
(
    id                 BINARY(16)   NOT NULL,
    cod_interno        INT          NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    status             BIT(1)       NOT NULL,
    datahora_cadastro  timestamp    NOT NULL,
    datahora_alteracao timestamp    NOT NULL,
    CONSTRAINT pk_cozinha PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE cozinha
    ADD CONSTRAINT uc_cozinha_codinterno UNIQUE (cod_interno);

CREATE TABLE forma_pagamento
(
    id                 BINARY(16)   NOT NULL,
    cod_interno        INT          NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    status             BIT(1)       NOT NULL,
    datahora_cadastro  timestamp    NOT NULL,
    datahora_alteracao timestamp    NOT NULL,
    CONSTRAINT pk_formapagamento PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE forma_pagamento
    ADD CONSTRAINT uc_formapagamento_codinterno UNIQUE (cod_interno);

CREATE TABLE grupo
(
    id                 BINARY(16)   NOT NULL,
    cod_interno        INT          NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    status             BIT(1)       NOT NULL,
    datahora_cadastro  timestamp    NOT NULL,
    datahora_alteracao timestamp    NOT NULL,
    CONSTRAINT pk_grupo PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE grupo
    ADD CONSTRAINT uc_grupo_codinterno UNIQUE (cod_interno);

CREATE TABLE permissao
(
    id                 BINARY(16)   NOT NULL,
    cod_interno        INT          NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    descricao          VARCHAR(100) NOT NULL,
    status             BIT(1)       NOT NULL,
    datahora_cadastro  datetime     NULL,
    datahora_alteracao datetime     NULL,
    CONSTRAINT pk_permissao PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE permissao
    ADD CONSTRAINT uc_permissao_codinterno UNIQUE (cod_interno);

CREATE TABLE grupo_permissao
(
    id_grupo     BINARY(16) NOT NULL,
    id_permissao BINARY(16) NOT NULL,
    CONSTRAINT pk_grupo_permissao PRIMARY KEY (id_grupo, id_permissao)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo FOREIGN KEY (id_grupo) REFERENCES grupo (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_on_permissao FOREIGN KEY (id_permissao) REFERENCES permissao (id);

CREATE TABLE restaurante
(
    id                   BINARY(16)   NOT NULL,
    cod_interno          INT          NOT NULL,
    id_cozinha           BINARY(16)   NOT NULL,
    nome                 VARCHAR(100) NOT NULL,
    taxa_frete           DECIMAL      NOT NULL,
    status               BIT(1)       NOT NULL,
    aberto               BIT(1)       NOT NULL,
    datahora_cadastro    timestamp    NOT NULL,
    datahora_alteracao   timestamp    NOT NULL,
    endereco_cep         VARCHAR(255) NULL,
    endereco_logradouro  VARCHAR(255) NULL,
    endereco_numero      VARCHAR(255) NULL,
    endereco_complemento VARCHAR(255) NULL,
    endereco_bairro      VARCHAR(255) NULL,
    endereco_id_cidade   BIGINT       NULL,
    CONSTRAINT pk_restaurante PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE restaurante
    ADD CONSTRAINT uc_restaurante_codinterno UNIQUE (cod_interno);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ID_COZINHA FOREIGN KEY (id_cozinha) REFERENCES cozinha (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_ENDERECO_ID_CIDADE FOREIGN KEY (endereco_id_cidade) REFERENCES cidade (id);

CREATE TABLE restaurante_forma_pagamento
(
    id_forma_pagamento BINARY(16) NOT NULL,
    id_restaurante     BINARY(16) NOT NULL,
    CONSTRAINT pk_restaurante_forma_pagamento PRIMARY KEY (id_forma_pagamento, id_restaurante)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_forma_pagamento FOREIGN KEY (id_forma_pagamento) REFERENCES forma_pagamento (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_resforpag_on_restaurante FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

CREATE TABLE produto
(
    id             BINARY(16)   NOT NULL,
    cod_interno    INT          NOT NULL,
    nome           VARCHAR(255) NOT NULL,
    descricao      VARCHAR(150) NOT NULL,
    preco          DECIMAL      NOT NULL,
    status         BIT(1)       NOT NULL,
    id_restaurante BINARY(16)   NULL,
    data_cadastro  timestamp    NOT NULL,
    data_alteracao timestamp    NOT NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE produto
    ADD CONSTRAINT uc_produto_codinterno UNIQUE (cod_interno);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_ID_RESTAURANTE FOREIGN KEY (id_restaurante) REFERENCES restaurante (id);

CREATE TABLE usuario
(
    id                 BINARY(16)   NOT NULL,
    cod_interno        INT          NOT NULL,
    nome               VARCHAR(100) NOT NULL,
    email              VARCHAR(100) NULL,
    senha              VARCHAR(30)  NULL,
    status             BIT(1)       NOT NULL,
    datahora_cadastro  timestamp    NOT NULL,
    datahora_alteracao timestamp    NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_codinterno UNIQUE (cod_interno);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_email UNIQUE (email);

CREATE TABLE usuario_grupo
(
    id_grupo   BINARY(16) NOT NULL,
    id_usuario BINARY(16) NOT NULL,
    CONSTRAINT pk_usuario_grupo PRIMARY KEY (id_grupo, id_usuario)
) engine = InnoDB
  default charset = utf8mb4;

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_grupo FOREIGN KEY (id_grupo) REFERENCES grupo (id);

ALTER TABLE usuario_grupo
    ADD CONSTRAINT fk_usugru_on_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id);