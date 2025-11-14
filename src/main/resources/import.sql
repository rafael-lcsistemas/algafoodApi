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

