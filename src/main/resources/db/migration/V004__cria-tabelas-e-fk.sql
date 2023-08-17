create table forma_pagamento (
id bigint not null auto_increment, 
descricao varchar(30) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo (
id bigint not null auto_increment,
nome varchar(255),
primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissao (
group_id bigint not null, 
permissao_id bigint not null
) engine=InnoDB default charset=utf8;

create table permissao (
id bigint not null auto_increment, 
nome varchar(255) not null, 
descricao varchar(255) not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table produto (
id bigint not null auto_increment, 
nome varchar(255) not null, 
descricao varchar(255) not null, 
preco decimal(38,2) not null, 
ativo bit not null, 
restaurante_id bigint not null, 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante (
id bigint not null auto_increment, 
nome varchar(255) not null, 
taxa_frete decimal(38,2) not null, 
cozinha_id bigint not null, 
data_atualizacao datetime not null, 
data_cadastro datetime not null, 
endereco_cidade_id bigint, 
endereco_bairro varchar(255), 
endereco_cep varchar(255), 
endereco_complemento varchar(255), 
endereco_logradouro varchar(255), 
endereco_numero varchar(255), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurante_forma_pagamento (
forma_pagamento_id bigint not null, 
restaurante_id bigint not null
) engine=InnoDB default charset=utf8;

create table usuario (
data_cadastro datetime, 
id bigint not null auto_increment, 
email varchar(255), 
nome varchar(255), 
senha varchar(255), 
primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
grupo_id bigint not null, 
usuario_id bigint not null
) engine=InnoDB default charset=utf8;

alter table grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);

alter table grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (group_id) references grupo (id);

alter table produto add constraint fk_produto_restaurante foreign key (restaurante_id) references restaurante (id);

alter table restaurante add constraint fk_restaurante_cozinha foreign key (cozinha_id) references cozinha (id);

alter table restaurante add constraint fk_restaurante_cidade foreign key (endereco_cidade_id) references cidade (id);

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_forma_pagamento foreign key (forma_pagamento_id) references forma_pagamento (id);

alter table restaurante_forma_pagamento add constraint fk_restaurante_forma_pagamento_restaurante foreign key (restaurante_id) references restaurante (id);

alter table usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id);

alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);