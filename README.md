🍔 Delivery API
  API REST desenvolvida em Java com Spring Boot que simula o backend de um sistema de delivery de comida, inspirado em plataformas como iFood / Uber Eats.
  O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, arquitetura em camadas, segurança com JWT, persistência com JPA/Hibernate e versionamento de banco com Flyway.

📚 Aprendizados
  Durante o desenvolvimento deste projeto foram explorados:
  Criação de APIs REST com Spring Boot
  Injeção de dependência com Spring
  Persistência com JPA e Hibernate
  Modelagem de entidades e relacionamentos
  Autenticação com JWT
  Segurança com Spring Security
  Versionamento de banco com Flyway
  Documentação de APIs
  Testes de endpoints com Postman
  Organização de projeto backend profissional

🚀 Tecnologias Utilizadas:
  Java 21
  Spring Boot
  Spring Web
  Spring Data JPA
  Spring Security
  JWT (JSON Web Token)
  Nimbus JOSE + JWT
  Flyway
  MySQL
  Lombok
  Maven
  Postman
  OpenAPI / Swagger

🧠 Conceitos Aplicados
  Durante o desenvolvimento deste projeto foram aplicados diversos conceitos importantes de backend:
  Arquitetura em Camadas
  O projeto foi estruturado seguindo uma separação clara de responsabilidades:
  Controller → Service → Repository → Database
  Controller
  Recebe requisições HTTP
  Define endpoints REST
  Service
  Regras de negócio
  Orquestra as operações
  Repository
  Comunicação com o banco
  Uso do Spring Data JPA

REST API Design
  A API segue os princípios REST:
  Método	Uso
  GET	Buscar recursos
  POST	Criar recursos
  PUT	Atualizar recursos
  DELETE	Remover recursos

🔐 Autenticação e Segurança
  A aplicação utiliza JWT (JSON Web Token) para autenticação e autorização.
  O token é gerado no login e deve ser enviado nas requisições protegidas.

🔑 Geração e Validação de Token
  A aplicação utiliza RSA Keys para assinatura do JWT.
  Configuração baseada em:
  JwtEncoder
  JwtDecoder
  NimbusJwtEncoder
  NimbusJwtDecoder

🗄 Persistência de Dados
  A aplicação utiliza Spring Data JPA com Hibernate para persistência de dados.
  
🧩 Versionamento de Banco
  Foi utilizado Flyway para controle de versão do banco de dados.
