INSERT INTO cozinha (id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha (id, nome) VALUES (2, 'Indiana');

INSERT INTO forma_pagamento(id, descricao) VALUES (1 ,"Cartao");
INSERT INTO forma_pagamento(id, descricao) VALUES (2 ,"Dinheiro");

INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Thai Gourmet', 10, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Thai Delivery', 9.50, 1, 2);
INSERT INTO restaurante (nome, taxa_frete, cozinha_id, forma_pagamento_id) VALUES ('Tuk Tuk Comida Indiana', 15, 2, 1);

INSERT INTO permissao (nome, descricao) VALUES ("Administrador", "Pode administrar o sistema");
INSERT INTO permissao (nome, descricao) VALUES ("Consumidor", "Pode realizar pedido");
INSERT INTO permissao (nome, descricao) VALUES ("Prestador de servico", "Pode se cadastrar no sistema");

INSERT INTO estado(id, nome) VALUES (1, "Sao Paulo");
INSERT INTO estado(id, nome) VALUES (2, "Rio de Janeiro");

INSERT INTO cidade(nome, estado_id) VALUES ("Campinas", 1);
INSERT INTO cidade(nome, estado_id) VALUES ("Niteroi", 2);
