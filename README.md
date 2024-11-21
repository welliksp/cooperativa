# API Cooperativa

Esta API permite a criação e gerenciamento de sessões de votação dentro de uma cooperativa. Ela utiliza Spring Boot, Kafka e integra com um serviço externo de validação de CPF.

## Visão Geral

### Funcionalidades

### 1. Gerenciamento de Pautas
#### ° Endpoint: /v1/pauta
#### ° Método: POST
#### ° Descrição: Cria um novo item de pauta para votação.
#### ° Corpo da Requisição:

```
{
"titulo": "Nome da Pauta",
"descricao": "Descricao da Pauta"
}
```

### 2. Gerenciamento de Sessão de Votação
#### ° Endpoint: /v1/sessao
#### ° Método: PUT
#### ° Descrição: Abre uma nova sessão de votação para um item de pauta específico. Um parâmetro de duração opcional define a duração da sessão em minutos (o padrão é 1 minuto).
#### ° Parâmetros da Requisição:

° pautaId: O ID do item da pauta. 
° duracao: (Opcional) A duração da sessão em minutos.
#### Corpo da Resposta:

```
{
  "id": 1,
  "pautaId": 1,
  "dateStart": "2024-07-13T12:00:00Z",
  "dateEnd": "2024-07-13T12:01:00Z",
  "createdAt": "2024-07-13T12:00:00Z" 
}
```

#### Endpoint: /v1/sessao/fechar-sessao
#### Método: PUT
#### Descrição: Fecha uma sessão de votação.
#### Parâmetros da Requisição:
° sessaoId: O ID da sessão a ser fechada.
#### Corpo da Resposta: 200 OK


### 3. Votação
#### Endpoint: /v1/votacao
#### Método: POST
#### Descrição: Registra um voto em uma sessão aberta. Integra-se com um serviço externo para validar o CPF.
#### Corpo da Requisição:

```
{
    "sessaoId": "1",
    "associadoId":"02813371287",
    "vote": "NAO"
}
```
#### Corpo da Resposta:
```
{
    "id": 1,
    "titulo": "PAUTA03 - ABAIXO IMPOSTO DE IMPORTACAO",
    "descricao": "Finalidade de abaixar a porcentagem do imposto de importacao de produtos",
    "associadoId": "02813371287",
    "vote": "NAO",
    "createdAt": "2024-11-21T19:48:39.543+00:00"
}
```
### 4. Resultados da Votação
#### Endpoint: /v1/votacao
#### Método: GET
#### Descrição: Recupera os resultados da votação de uma sessão.
#### Parâmetros da Requisição:
 °sessaoId: O ID da sessão de votação.
#### Corpo da Resposta:
```
{
    "sessaoId": 1,
    "totalVotos": 1,
    "votosAceitos": 0,
    "votosNegados": 1,
    "result": "Negados"
}
```

## Integração com Kafka
Os resultados da votação são publicados no tópico resultado-votacao do Kafka em tempo real à medida que os votos são registrados. O formato da mensagem é uma string JSON contendo as contagens de votos. Isso permite que outros serviços consumam e processem os resultados.

## Arquitetura
A aplicação é construída usando Spring Boot e segue uma arquitetura em camadas:

#### °Controllers: Lida com as requisições e respostas recebidas.
#### °Services: Implementa a lógica de negócios.
#### °Repositories: Interage com o banco de dados.
#### °Kafka Producer: Envia mensagens para o Kafka.

## Tratamento de Erros
A API usa códigos de status HTTP padrão para indicar sucesso ou falha. Erros de validação retornam um 400 Bad Request com detalhes das falhas de validação.

## Dependências
#### Spring Boot
#### Spring Kafka
#### (Serviço Externo de Validação de CPF - não detalhado aqui conforme as instruções do prompt.)

Esta documentação fornece uma visão geral completa da API Cooperativa. Ela detalha os endpoints disponíveis, os formatos de solicitação e resposta e a integração com o Kafka. Essas informações devem permitir que os desenvolvedores integrem e utilizem a API de forma eficaz.