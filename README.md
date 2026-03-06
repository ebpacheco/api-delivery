  **Delivery API**<br>
  API REST desenvolvida em Java com Spring Boot que simula o backend de um sistema de delivery de comida, inspirado em plataformas como iFood / Uber Eats.<br>
  O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, arquitetura em camadas, segurança com JWT, persistência com JPA/Hibernate e versionamento de banco com Flyway.<br>

  **Aprendizados**<br>
  Durante o desenvolvimento deste projeto foram explorados:<br>
  Criação de APIs REST com Spring Boot<br>
  Injeção de dependência com Spring<br>
  Persistência com JPA e Hibernate<br>
  Modelagem de entidades e relacionamentos<br>
  Autenticação com JWT<br>
  Segurança com Spring Security<br>
  Versionamento de banco com Flyway<br>
  Documentação de APIs<br>
  Testes de endpoints com Postman<br>
  Organização de projeto backend profissional<br>

 **Tecnologias Utilizadas:**<br>
  Java 21<br>
  Spring Boot<br>
  Spring Web<br>
  Spring Data JPA<br>
  Spring Security<br>
  JWT (JSON Web Token)<br>
  Nimbus JOSE + JWT<br>
  Flyway<br>
  MySQL<br>
  Lombok<br>
  Maven<br>
  Postman<br>
  OpenAPI / Swagger<br>

 **Conceitos Aplicados**<br>
  Durante o desenvolvimento deste projeto foram aplicados diversos conceitos importantes de backend:<br>
  Arquitetura em Camadas<br>
  
  O projeto foi estruturado seguindo uma separação clara de responsabilidades:<br>
  Controller → Service → Repository → Database<br>
  
  *Controller*<br>
  Recebe requisições HTTP<br>
  Define endpoints REST<br>
  
  *Service*<br>
  Regras de negócio<br>
  Orquestra as operações<br>
  
 *Repository*<br>
  Comunicação com o banco<br>
  Uso do Spring Data JPA<br>

 **Autenticação e Segurança**<br>
  A aplicação utiliza JWT (JSON Web Token) para autenticação e autorização.<br>
  O token é gerado no login e deve ser enviado nas requisições protegidas.<br>

 **Geração e Validação de Token**<br>
  A aplicação utiliza RSA Keys para assinatura do JWT.<br>
  Configuração baseada em:<br>
  JwtEncoder<br>
  JwtDecoder<br>
  NimbusJwtEncoder<br>
  NimbusJwtDecoder<br>

 **Persistência de Dados**<br>
  A aplicação utiliza Spring Data JPA com Hibernate para persistência de dados.<br>
  
 **Versionamento de Banco**<br>
  Foi utilizado Flyway para controle de versão do banco de dados.<br>
