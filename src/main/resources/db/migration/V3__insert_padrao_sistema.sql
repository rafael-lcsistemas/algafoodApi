insert into forma_pagamento(id, cod_interno, nome, status, datahora_cadastro, datahora_alteracao)
values (UUID_TO_BIN('32f63088-cdd5-45b4-bf67-918ee68ce79c'), 1, 'Dinheiro', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('2a62d670-d539-4125-ba62-5d77ffdbbd38'), 2, 'Pix', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('fcfcb4f0-715c-4dee-be4a-49283080c993'), 3, 'Cartão de Crédito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('556100ad-d227-4820-b2c2-8d0598b4efa9'), 4, 'Cartão de Débito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO usuario(id, cod_interno, nome, email, senha, status, datahora_cadastro, datahora_alteracao)
VALUES (UUID_TO_BIN('9ea84861-684a-4ae3-ba4e-a8466c7cdd0d'), 1, 'ADMIN', 'admin@admin.com.br', '123456', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO cozinha(id, cod_interno, nome, status, datahora_cadastro, datahora_alteracao)
VALUES (UUID_TO_BIN('b6a3ff71-c0cf-4606-88e2-f023890c60a4'), 1, 'PADRAO', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO restaurante(id, cod_interno, nome, taxa_frete, status, aberto, id_cozinha, datahora_cadastro, datahora_alteracao)
VALUES (UUID_TO_BIN('d8d45d27-08bf-4e87-b931-7b22aa479bd8'), 1, 'PADRAO', 0.000, true, 1, UUID_TO_BIN('b6a3ff71-c0cf-4606-88e2-f023890c60a4'), UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO restaurante_forma_pagamento(id_forma_pagamento, id_restaurante)
VALUES (UUID_TO_BIN('32f63088-cdd5-45b4-bf67-918ee68ce79c'), UUID_TO_BIN('d8d45d27-08bf-4e87-b931-7b22aa479bd8')),
       (UUID_TO_BIN('2a62d670-d539-4125-ba62-5d77ffdbbd38'), UUID_TO_BIN('d8d45d27-08bf-4e87-b931-7b22aa479bd8')),
       (UUID_TO_BIN('fcfcb4f0-715c-4dee-be4a-49283080c993'), UUID_TO_BIN('d8d45d27-08bf-4e87-b931-7b22aa479bd8')),
       (UUID_TO_BIN('556100ad-d227-4820-b2c2-8d0598b4efa9'), UUID_TO_BIN('d8d45d27-08bf-4e87-b931-7b22aa479bd8'));

INSERT INTO grupo(id, cod_interno, nome, status, datahora_cadastro, datahora_alteracao)
VALUES (UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'), 1, 'MASTER', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO permissao(id, cod_interno, nome, descricao, status, datahora_cadastro, datahora_alteracao)
VALUES (UUID_TO_BIN('ec858c42-616b-4467-ba42-4f138767c5bd'), 1, 'LOGIN', 'Permite realizar login no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('e8846d5c-066f-42df-88c9-795a5912b90d'), 2, 'CADASTRAR', 'Permite cadastrar novos registros no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('ad577ab2-a61d-4df2-bbeb-086055f981c9'), 3, 'CONSULTAR', 'Permite consultar dados no sistema', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       (UUID_TO_BIN('313cbd87-2d0d-45db-a1cc-62f783299557'), 4, 'ATUALIZAR', 'Permite atualizar registros existentes', 1, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO grupo_permissao(id_grupo, id_permissao)
VALUES (UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'), UUID_TO_BIN('ec858c42-616b-4467-ba42-4f138767c5bd')),
       (UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'), UUID_TO_BIN('e8846d5c-066f-42df-88c9-795a5912b90d')),
       (UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'), UUID_TO_BIN('ad577ab2-a61d-4df2-bbeb-086055f981c9')),
       (UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'), UUID_TO_BIN('313cbd87-2d0d-45db-a1cc-62f783299557'));

INSERT INTO usuario_grupo(id_usuario, id_grupo)
VALUES (UUID_TO_BIN('9ea84861-684a-4ae3-ba4e-a8466c7cdd0d'), UUID_TO_BIN('121e45b9-a75c-46db-acdf-150210d949f8'));
