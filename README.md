![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

# Projeto Spring Bank API

Este é um projeto Spring em Java que implementa uma API bancária para lidar com transações entre clientes e empresas. O sistema inclui a criação de clientes e empresas, a realização de transações, depósitos e saques, e a aplicação de taxas sobre as transações.

## Desafio

O desafio consiste em criar um sistema com pelo menos dois tipos de usuários: Empresa e Cliente. Os CPFs (para Cliente) e CNPJs (para Empresa) devem ser validados. Cada Empresa deve ter pelo menos um tipo de taxa de sistema que será aplicada no momento da transação (saque ou depósito). As Empresas devem ter um saldo que é afetado pelos depósitos e saques realizados pelos Clientes, considerando as taxas de administração do sistema. Os Clientes podem fazer depósitos e saques nas Empresas, dependendo dos saldos das empresas.

## Estrutura do Projeto

O projeto é estruturado em classes Java utilizando o framework Spring Boot. Abaixo estão os principais componentes do projeto:

### Controllers

#### BankAPIController

Este controlador é responsável por expor os endpoints da API. Ele permite a criação de bancos, clientes, a realização de transações, depósitos e saques.

### DTOs (Data Transfer Objects)

- `BankRecordDto`: Representa os dados necessários para criar um banco.
- `ClientRecordDto`: Representa os dados necessários para criar um cliente.
- `DepositDto`: Representa os dados necessários para realizar um depósito.
- `TransactionDto`: Representa os dados necessários para realizar uma transação entre clientes.
- `WithdrawDto`: Representa os dados necessários para realizar um saque.

### Models

- `BankModel`: Representa um banco com informações como CNPJ, nome, saldo e taxa.
- `ClientModel`: Representa um cliente com informações como CPF, nome, saldo e CNPJ do banco associado.

### Repositories

- `BankRepository`: Interface que fornece métodos de acesso aos dados dos bancos.
- `ClientRepository`: Interface que fornece métodos de acesso aos dados dos clientes.

### Services

- `BankService`: Contém lógica de negócios para depósitos e saques, aplicando as taxas apropriadas.

## Configuração do Banco de Dados

O projeto utiliza um banco de dados MySQL para armazenar as informações. Para configurar o banco de dados, siga as instruções abaixo.

### Configuração do `application.properties`

Adicione as seguintes configurações no seu arquivo `application.properties` para conectar o aplicativo ao banco de dados MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/{bancodedados}
spring.datasource.username={seuuser}
spring.datasource.password={suasenha}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```

Certifique-se de que o MySQL está instalado e em execução na porta padrão (3306). Se você estiver usando um usuário ou senha diferentes, ajuste as configurações de acordo.

### Criação do Banco de Dados

Antes de iniciar o aplicativo, crie um banco de dados chamado `apibank` no seu servidor MySQL.

```sql
CREATE DATABASE apibank;
```

Isso garantirá que o aplicativo tenha um local para armazenar e recuperar os dados.

Após configurar o banco de dados, inicie a aplicação Spring Boot. O Hibernate, configurado com `ddl-auto=update`, criará automaticamente as tabelas necessárias com base nas entidades do seu projeto.

Lembre-se de ajustar as configurações do banco de dados conforme necessário para atender aos requisitos específicos do seu ambiente.


## Testes com Postman

### Endpoint: Criação de Banco

**Request:**
```http
POST /bank/create
Content-Type: application/json

{
  "name": "Banco XPTO",
  "CNPJ": 12345678901234,
  "tax": 0.05
}
```

**Response:**
```json
{
  "id": "uuid-gerado",
  "CNPJ": 12345678901234,
  "name": "Banco XPTO",
  "balance": 0.0,
  "tax": 0.05
}
```

### Endpoint: Criação de Cliente

**Request:**
```http
POST /client/create
Content-Type: application/json

{
  "name": "Cliente A",
  "CPF": 98765432198,
  "bankCNPJ": 12345678901234,
  "balance": 1000.0
}
```

**Response:**
```json
{
  "id": "uuid-gerado",
  "CPF": 98765432198,
  "name": "Cliente A",
  "balance": 1000.0,
  "bankCNPJ": 12345678901234
}
```

### Endpoint: Realização de Transação

**Request:**
```http
POST /transaction
Content-Type: application/json

{
  "CPF": 98765432198,
  "CPF2": 12345678901,
  "value": 500.0,
  "CNPJ": 12345678901234
}
```

**Response:**
```json
{
  "message": "Transação realizada com sucesso."
}
```

### Endpoint: Depósito

**Request:**
```http
POST /deposit
Content-Type: application/json

{
  "value": 1000.0,
  "CPF": 98765432198,
  "bankCNPJ": 12345678901234
}
```

**Response:**
```json
{
  "id": "uuid-gerado",
  "CNPJ": 12345678901234,
  "name": "Banco XPTO",
  "balance": 1000.0,
  "tax": 0.05
}
```

### Endpoint: Saque

**Request:**
```http
POST /withdraw
Content-Type: application/json

{
  "value": 200.0,
  "CPF": 98765432198,
  "bankCNPJ": 12345678901234
}
```

**Response:**
```json
{
  "id": "uuid-gerado",
  "CNPJ": 12345678901234,
  "name": "Banco XPTO",
  "balance": 795.0,
  "tax": 0.05
}
```

Estes são exemplos de requisições utilizando o Postman para testar os endpoints da API. Lembre-se de ajustar os valores conforme necessário.

## Possíveis Melhorias

O projeto atual oferece uma implementação funcional da API bancária, mas sempre há espaço para melhorias e aprimoramentos. Abaixo estão algumas sugestões de melhorias que podem ser consideradas:

1. **Autenticação e Autorização:** Implementar um sistema de autenticação e autorização para proteger os endpoints sensíveis da API, garantindo que apenas usuários autorizados possam realizar certas operações.

2. **Documentação da API:** Utilizar uma ferramenta como o Swagger para documentar a API de forma mais completa, fornecendo detalhes sobre os endpoints, parâmetros, respostas e exemplos de uso.

3. **Manuseio de Exceções:** Melhorar o manuseio de exceções para fornecer mensagens de erro mais significativas e informações úteis para o desenvolvedor ao lidar com casos excepcionais.

4. **Validações Adicionais:** Reforçar as validações de entrada nos DTOs e nos serviços para garantir a integridade e consistência dos dados.

5. **Testes Automatizados:** Expandir a cobertura de testes automatizados, incluindo testes de unidade, testes de integração e testes end-to-end. Isso ajudará a garantir a estabilidade e confiabilidade do sistema.

6. **Logging:** Adicionar registros de logs adequados em diferentes partes do código para facilitar a identificação e resolução de problemas durante o monitoramento do sistema.

7. **Tratamento de Transações:** Aprimorar o tratamento de transações para garantir consistência em operações que envolvam atualizações em mais de uma entidade.

8. **Paginação e Ordenação:** Implementar paginização e ordenação para os endpoints que retornam listas de dados, permitindo uma melhor manipulação de grandes conjuntos de resultados.

9. **Segurança das Senhas:** Se houver autenticação de usuários, garantir que as senhas sejam armazenadas de forma segura usando técnicas de hash e sal.

10. **Monitoramento e Métricas:** Integrar ferramentas de monitoramento e métricas para obter insights sobre o desempenho do aplicativo, identificar gargalos e tomar decisões informadas sobre otimizações.

11. **Internacionalização:** Se o projeto for destinado a uma audiência global, considere a internacionalização (i18n) para fornecer mensagens em vários idiomas.

12. **Melhorias na Lógica de Taxas:** Avaliar a lógica de cálculo de taxas e considerar a possibilidade de permitir diferentes tipos de taxas para diferentes transações.

Essas são apenas sugestões e as melhorias a serem implementadas podem variar com base nos requisitos específicos do projeto e nas metas de desenvolvimento. É sempre recomendável revisar periodicamente o código-fonte e aprimorar continuamente o sistema à medida que novas necessidades surgem.
