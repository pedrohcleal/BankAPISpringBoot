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
