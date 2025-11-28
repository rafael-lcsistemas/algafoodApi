insert into forma_pagamento(nome, ativo, datahora_cadastro, datahora_alteracao)
values ('Dinheiro', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Pix', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cartão de Crédito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP()),
       ('Cartão de Débito', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO usuario(id, nome, email, senha, ativo, datahora_cadastro, datahora_alteracao)
VALUES (1, 'ADMIN', 'admin@admin.com.br', '123456', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO cozinha(id, nome, ativo, datahora_cadastro, datahora_alteracao)
VALUES (1, 'PADRAO', true, UTC_TIMESTAMP(), UTC_TIMESTAMP());

INSERT INTO grupo(id, nome, ativo, datahora_cadastro, datahora_alteracao)
VALUES (1, 'MASTER', true, UTC_TIMESTAMP(), UTC_TIMESTAMP())