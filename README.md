_Repositório apenas para estudo_

# Project 105 - Microserviços Quarkus com Consul Discovery

Demonstração da utilização do Consul Discovery com Microserviços Quarkus.

<br>

## Arquitetura

Nosso sistema baseado em microsserviços de amostra consiste nos seguintes serviços:

- **employee-service**
  - Serviço contendo o primeiro de nossos microsserviços de amostra que permite realizar o CRUD de funcionários
- **department-service**
  - Serviço contendo o segundo de nossos microsserviços de amostra que permite realizar o CRUD de departamentos.
  - Ele se comunica com _employee-service_.
- **organization-service**
  - Serviço que contém o terceiro de nossos microsserviços de amostra que permite realizar o CRUD de organizações.
  - Ele se comunica com _employee-service_ e _department-service_.
- **discovery-service**
  - Serviço de Descoberta de Seviços utilizando Consul
- **gateway-service**
  - Serviço que usa o Spring Cloud Gateway para executar o aplicativo Spring Boot que atua como um proxy/gateway em nossa arquitetura.

## Instruções

Criar rede para co contexto company

```bash
docker network create cx-company-net
```
