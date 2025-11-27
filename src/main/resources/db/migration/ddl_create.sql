create table cidade
(
    id        bigint not null auto_increment,
    nome      varchar(255),
    id_estado bigint,
    primary key (id)
) engine = InnoDB
create table cozinha
(
    id    bigint       not null auto_increment,
    ativo bit,
    nome  varchar(255) not null,
    primary key (id)
) engine = InnoDB
create table estado
(
    id   bigint       not null auto_increment,
    nome varchar(255) not null,
    uf   varchar(255) not null,
    primary key (id)
) engine = InnoDB
create table forma_pagamento
(
    id   bigint not null auto_increment,
    nome varchar(255),
    primary key (id)
) engine = InnoDB
create table grupo
(
    id        bigint       not null auto_increment,
    descricao varchar(255) not null,
    primary key (id)
) engine = InnoDB
create table grupo_permissao
(
    id_grupo     bigint not null,
    id_permissao bigint not null
) engine = InnoDB
create table permissao
(
    id        bigint       not null auto_increment,
    descricao varchar(255),
    nome      varchar(255) not null,
    primary key (id)
) engine = InnoDB
create table produto
(
    id             bigint         not null auto_increment,
    ativo          bit            not null,
    data_alteracao TIMESTAMP      not null,
    data_cadastro  TIMESTAMP      not null,
    descricao      varchar(255),
    nome           varchar(255)   not null,
    preco          decimal(19, 2) not null,
    id_restaurante bigint,
    primary key (id)
) engine = InnoDB
create table restaurante
(
    id                   bigint       not null auto_increment,
    data_alteracao       TIMESTAMP    not null,
    data_cadastro        TIMESTAMP    not null,
    endereco_bairro      varchar(255),
    endereco_cep         varchar(255),
    endereco_complemento varchar(255),
    endereco_logradouro  varchar(255),
    endereco_numero      varchar(255),
    nome                 varchar(255) not null,
    taxa_frete           decimal(19, 2),
    id_cozinha           bigint       not null,
    endereco_id_cidade   bigint,
    primary key (id)
) engine = InnoDB
create table restaurante_forma_pagamento
(
    id_restaurante     bigint not null,
    id_forma_pagamento bigint not null
) engine = InnoDB
create table usuario
(
    id            bigint       not null auto_increment,
    data_cadastro datetime(6),
    email         varchar(255),
    nome          varchar(255) not null,
    senha         varchar(255),
    primary key (id)
) engine = InnoDB
create table usuario_grupo
(
    id_usuario bigint not null,
    id_grupo   bigint not null
) engine = InnoDB
alter table cidade
    add constraint FKjn311p28f0okajvcboowr5kpo foreign key (id_estado) references estado (id)
alter table grupo_permissao
    add constraint FKfdid07dqkcx8cc53nj7rkmvcl foreign key (id_permissao) references permissao (id)
alter table grupo_permissao
    add constraint FK9oesxnxaml2s24exucm3jr4fr foreign key (id_grupo) references grupo (id)
alter table produto
    add constraint FKi9wilbw69bh3m9rw7xs7sqmah foreign key (id_restaurante) references restaurante (id)
alter table restaurante
    add constraint FKs0dxabd90oowkwaqh42dkbfxi foreign key (id_cozinha) references cozinha (id)
alter table restaurante
    add constraint FKta27ou70q5qp6bsd2ts913rrb foreign key (endereco_id_cidade) references cidade (id)
alter table restaurante_forma_pagamento
    add constraint FKi5u3gytpsof4uedhurtis2r8q foreign key (id_forma_pagamento) references forma_pagamento (id)
alter table restaurante_forma_pagamento
    add constraint FK9urxjm86vev9pa1ai1awuenvc foreign key (id_restaurante) references restaurante (id)
alter table usuario_grupo
    add constraint FKcu6om65mvqr6ct95ijgqgx7ww foreign key (id_grupo) references grupo (id)
alter table usuario_grupo
    add constraint FK9huj1upwjyabwkwnpnhnernnu foreign key (id_usuario) references usuario (id)