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

## Databases

```properties
## H2
quarkus.datasource.db-kind=h2
quarkus.datasource.jdbc.driver=org.h2.Driver
quarkus.datasource.jdbc.url=jdbc:h2:mem:default
quarkus.hibernate-orm.dialect=org.hibernate.dialect.PostgreSQL95Dialect

## PostgreSQL
quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.driver=org.postgresql.Driver
quarkus.datasource.jdbc.url=jdbc:postgresql://${POSTGRES_HOST:localhost}:${POSTGRES_PORT:5433}/${POSTGRES_DB:employee}
quarkus.datasource.username=${POSTGRES_USER:postgres}
quarkus.datasource.password=${POSTGRES_PASSWORD:postgres}
quarkus.hibernate-orm.dialect=org.hibernate.dialect.PostgreSQL95Dialect
```

## Docker

Criar rede para contexto company

```
docker volume create consul_data --label description='Persistent data for consul'
```

```bash
docker network create project105-net
```

### employee-db

Acessar o terminal (em modo administrador) na raiz do `employee-db`

```
docker build -t project105/employee-db .
```

### employee-api

Acessar o terminal (em modo administrador) na raiz do `employee`

```bash
mvn package -DskipTests
```

Gerar imagem Docker, usando o comando:

```
docker build -t project105/employee-api .
```

Em seguida, execute o contêiner usando:

```

docker run -i --rm -P --network project105-net project105/employee-api

docker run -i --rm -p 8081:8080 --network project105-net project105/employee-api

```

## Endpoits

**employee-api**

![employee-api](doc/img/employee-api.png)

**department-api**

![employee-api](doc/img/department-api.png)

**organization-api**

![employee-api](doc/img/organization-api.png)

**getwary-api**

O gateway de API está disponível na porta `8080`. Ele usa prefixo `/api`.

Aqui estão alguns endpoints para listar todos os funcionários, departamentos e organizações disponíveis.

- http://localhost:8080/api/employees
- http://localhost:8080/api/departments
- http://localhost:8080/api/organizations

## Referências

- https://piotrminkowski.com/2020/11/24/quarkus-microservices-with-consul-discovery/
- https://quarkiverse.github.io/quarkiverse-docs/quarkus-config-extensions/dev/consul.html
