-- Cozinhas
insert into cozinha(nome) values('Paraense');
insert into cozinha(nome) values('Maranhense');
insert into cozinha(nome) values('Mineira');
-- Forma Pagamento
insert into forma_pagamento(nome) values('Dinheiro');
insert into forma_pagamento(nome) values('Pix');
insert into forma_pagamento(nome) values('Cartão de Crédito');
insert into forma_pagamento(nome) values('Cartão de Débito');
-- Restaurantes
insert into restaurante(nome, taxa_Frete, id_cozinha, id_forma_pagamento) values('Aurora', 5.0, 1, 1);
insert into restaurante(nome, taxa_Frete, id_cozinha, id_forma_pagamento) values('Imperatriz', 5.0, 2, 2);
insert into restaurante(nome, taxa_Frete, id_cozinha, id_forma_pagamento) values('Belo Horizonte', 10.0, 3, 4);
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

