set foreign_key_checks= 0;
SET SQL_SAFE_UPDATES = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;
delete from foto_produto;

set foreign_key_checks= 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;
alter table pedido auto_increment = 1;

INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

INSERT INTO forma_pagamento(id, descricao) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento(id, descricao) VALUES (2, 'Cartão de débito'); 
INSERT INTO forma_pagamento(id, descricao) VALUES (3, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id , nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

INSERT INTO estado(id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estado(id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado(id, nome) VALUES (3, 'Ceará');

INSERT INTO cidade(id, nome, estado_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO cidade(id, nome, estado_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO cidade(id, nome, estado_id) VALUES (3, 'São Paulo', 2);
INSERT INTO cidade(id, nome, estado_id) VALUES (4, 'Campinas', 2);
INSERT INTO cidade(id, nome, estado_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (1, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) VALUES (2, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) VALUES (3, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua Joao Pinheiro', '100', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 0, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into usuario (id, nome, email, senha, data_cadastro) values (1, 'Eduardo', 'eduardo@teste.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (2, 'Natany', 'natany@teste.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (3, 'Renoia', 'renoia@teste.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (4, 'Ariane', 'ariane@teste.com', '123', utc_timestamp);
insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

insert into grupo_permissao (group_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 1), (1, 5), (3, 5);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, '283ae19f-cd28-4f42-b9c6-c1da2d03bee2', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, uuid(), 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (3, uuid(), 1, 1, 1, 1, '38400-010', 'Rua Teresina', '300', 'Apto 12', 'Brasil', 'CRIADO', utc_timestamp, 27.90, 10, 37.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (4, 3, 1, 1, 27.90, 27.90, 'Sem molho');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (4, uuid(), 1, 1, 1, 1, '38400-020', 'Rua Natal', '400', 'Apto 01', 'Brasil', 'CRIADO', utc_timestamp, 131.20, 10, 141.20);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (5, 4, 1, 2, 65.60, 131.20, 'Com Talher');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (5, uuid(), 1, 1, 1, 1, '38400-020', 'Rua Ibo', '400', 'Apto 01', 'Brasil', 'ENTREGUE', '2024-08-01 21:35:00', 131.20, 10, 141.20);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (6, 5, 1, 2, 65.60, 131.20, 'Com Talher');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (6, uuid(), 1, 1, 1, 1, '38400-020', 'Rua Ibitirama', '2051', 'Apto 03', 'Brasil', 'CONFIRMADO', '2024-08-04 21:35:00', 131.20, 10, 141.20);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (7, 6, 1, 2, 65.60, 131.20, 'Com Talher');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (7, uuid(), 1, 1, 1, 1, '38400-020', 'Rua Ibitirama', '2051', 'Apto 03', 'Brasil', 'CONFIRMADO', '2024-08-05 02:20:00', 131.20, 10, 141.20);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao)
values (8, 7, 1, 2, 65.60, 131.20, 'Com Talher');
