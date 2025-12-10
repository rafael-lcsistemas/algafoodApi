insert into forma_pagamento(id, nome, status, datahora_cadastro, datahora_alteracao)
values (1, 'Dinheiro', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (2, 'Pix', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (3, 'Cartão de Crédito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (4, 'Cartão de Débito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO usuario(id, nome, email, senha, status, datahora_cadastro, datahora_alteracao)
VALUES (1, 'ADMIN', 'admin@admin.com.br', '123456', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO cozinha(id, nome, status, datahora_cadastro, datahora_alteracao)
VALUES (1, 'PADRAO', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO restaurante(id, nome, taxa_frete, status, id_cozinha, datahora_cadastro, datahora_alteracao)
VALUES (1, 'PADRAO', 0.000, true, 1, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO restaurante_forma_pagamento(id_forma_pagamento, id_restaurante)
VALUES (1, 1), (2, 1), (3, 1), (4, 1);

INSERT INTO grupo(id, nome, status, datahora_cadastro, datahora_alteracao)
VALUES (1, 'MASTER', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO permissao(id, nome, descricao, status, datahora_cadastro, datahora_alteracao)
VALUES (1, 'LOGIN', 'Permite realizar login no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (2, 'CADASTRAR', 'Permite cadastrar novos registros no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (3, 'CONSULTAR', 'Permite consultar dados no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (4, 'ATUALIZAR', 'Permite atualizar registros existentes', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO grupo_permissao(id_grupo, id_permissao)
VALUES (1, 1), (1, 2), (1, 3), (1, 4);

INSERT INTO usuario_grupo(id_usuario, id_grupo)
VALUES (1, 1);
