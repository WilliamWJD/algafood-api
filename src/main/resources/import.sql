insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Brasileira');
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Will Sabores', 10.0, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('GunGun Lanches', 5.0, 3);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Karol Dogs', 5.0, 3);
insert into forma_pagamento (descricao) values ('Cartão de Crédito');
insert into forma_pagamento (descricao) values ('Cartão de Débito');
insert into permissao (nome, descricao) values ('Administrador','Acesso total');
insert into estado (nome) values ('São Paulo');
insert into cidade (nome, estado_id) values ('Sumaré', 1);