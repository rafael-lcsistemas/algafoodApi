-- Cozinhas
insert into cozinha(nome) values('Paraense');
insert into cozinha(nome) values('Maranhense');
insert into cozinha(nome) values('Mineira');
-- Forma Pagamento
insert into forma_pagamento(nome) values('Dinheiro');
insert into forma_pagamento(nome) values('Pix');
insert into forma_pagamento(nome) values('Cartão de Crédito');
insert into forma_pagamento(nome) values('Cartão de Débito');
-- Estados
insert into estado(nome, uf) values('Pará', 'PA');
insert into estado(nome, uf) values('Maranhão', 'MA');
insert into estado(nome, uf) values('Minas Gerais', 'MG');
-- Cidades
insert into cidade(nome, id_estado) values('Aurora do Pará', 1);
insert into cidade(nome, id_estado) values('Mâe do Rio', 1);
insert into cidade(nome, id_estado) values('imperatriz', 2);
insert into cidade(nome, id_estado) values('Sãi Luiz', 2);
insert into cidade(nome, id_estado) values('Contagem', 3);
insert into cidade(nome, id_estado) values('Caldas', 3);
-- Restaurantes
insert into restaurante(nome, taxa_Frete, id_cozinha, endereco_cep, endereco_logradouro, endereco_bairro, endereco_numero, endereco_complemento, endereco_id_cidade, data_cadastro, data_alteracao) values('Aurora', 0.0, 1, '68658-000', 'Av Bernado sayão', 'Centro', '1000', 'Apto 101', 1, utc_timestamp, utc_timestamp);
insert into restaurante(nome, taxa_Frete, id_cozinha, data_cadastro, data_alteracao) values('Imperatriz', 5.0, 2, utc_timestamp, utc_timestamp);
insert into restaurante(nome, taxa_Frete, id_cozinha, data_cadastro, data_alteracao) values('Belo Horizonte', 10.0, 3, utc_timestamp, utc_timestamp);
-- Restaurantes & Formas de pagamento
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(1, 1);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(1, 2);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(2, 1);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(2, 2);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(2, 3);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(3, 1);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(3, 2);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(3, 3);
insert into restaurante_forma_pagamento(id_restaurante, id_forma_pagamento) values(3, 4);
-- Produto
INSERT INTO produto (nome, descricao, preco, ativo, id_restaurante, data_cadastro, data_alteracao) VALUES ('Hambúrguer Clássico', 'Pão brioche, carne 180g, queijo, alface e tomate.', 28.90, true, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO produto (nome, descricao, preco, ativo, id_restaurante, data_cadastro, data_alteracao) VALUES ('Pizza Margherita', 'Molho de tomate, mussarela, manjericão fresco e azeite.', 42.00, true, 2, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO produto (nome, descricao, preco, ativo, id_restaurante, data_cadastro, data_alteracao) VALUES ('Lasanha Bolonhesa', 'Massa fresca, molho bolonhesa artesanal e parmesão gratinado.', 36.50, true, 1, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO produto (nome, descricao, preco, ativo, id_restaurante, data_cadastro, data_alteracao) VALUES ('Salada Caesar', 'Alface americana, frango grelhado, croutons e molho caesar.', 24.00, true, 2, UTC_TIMESTAMP, UTC_TIMESTAMP);
INSERT INTO produto (nome, descricao, preco, ativo, id_restaurante, data_cadastro, data_alteracao) VALUES ('Café Espresso', 'Café 100% arábica, torra média, servido curto.', 6.00, true, 3, UTC_TIMESTAMP, UTC_TIMESTAMP);
-- Permissão
INSERT INTO permissao (nome, descricao) VALUES ('CADASTRAR_COZINHA', 'Permite cadastrar uma nova cozinha');
INSERT INTO permissao (nome, descricao) VALUES ('ATUALIZAR_COZINHA', 'Permite atualizar uma cozinha existente');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_COZINHA', 'Permite remover uma cozinha');
INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR_COZINHA', 'Permite consultar uma cozinha');
INSERT INTO permissao (nome, descricao) VALUES ('CADASTRAR_RESTAURANTE', 'Permite cadastrar um novo restaurante');
INSERT INTO permissao (nome, descricao) VALUES ('ATUALIZAR_RESTAURANTE', 'Permite atualizar um restaurante existente');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_RESTAURANTE', 'Permite remover um restaurante');
INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR_RESTAURANTE', 'Permite consultar um restaurante');
INSERT INTO permissao (nome, descricao) VALUES ('CADASTRAR_PRODUTO', 'Permite cadastrar um novo produto');
INSERT INTO permissao (nome, descricao) VALUES ('ATUALIZAR_PRODUTO', 'Permite atualizar um produto existente');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_PRODUTO', 'Permite remover um produto');
INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR_PRODUTO', 'Permite consultar um produto');
INSERT INTO permissao (nome, descricao) VALUES ('CADASTRAR_CLIENTE', 'Permite cadastrar um novo cliente');
INSERT INTO permissao (nome, descricao) VALUES ('ATUALIZAR_CLIENTE', 'Permite atualizar um cliente existente');
INSERT INTO permissao (nome, descricao) VALUES ('DELETAR_CLIENTE', 'Permite remover um cliente');
INSERT INTO permissao (nome, descricao) VALUES ('CONSULTAR_CLIENTE', 'Permite consultar um cliente');
-- Grupo
INSERT INTO grupo (descricao) VALUES ('ADMINISTRADOR');
INSERT INTO grupo (descricao) VALUES ('GERENTE');
INSERT INTO grupo (descricao) VALUES ('CAIXA');
-- Grupo Permissões
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 2);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 3);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 4);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 5);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 6);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 7);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 8);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 9);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 10);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 11);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 12);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 13);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 14);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 15);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 16);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 1);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 2);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 3);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 4);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 5);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 6);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 7);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 8);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 9);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 10);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 11);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 12);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 13);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 14);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 15);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 16);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (3, 4);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (3, 8);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (3, 12);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (3, 16);
-- Usuario
INSERT INTO usuario (nome, email, senha) VALUES ('Rafael Nunes', 'rafael@email.com', '123456');
INSERT INTO usuario (nome, email, senha) VALUES ('Ana Souza', 'ana.souza@email.com', '123456');
INSERT INTO usuario (nome, email, senha) VALUES ('Carlos Lima', 'carlos.lima@email.com', '123456');
-- Usuario Grupo
INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (1, 1);
INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (2, 2);
INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (3, 3);
