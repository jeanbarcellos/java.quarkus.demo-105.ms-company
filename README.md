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


## Docker

### **Opção 1 - Apenas rodar projeto | Compose com imagens do Docker HUB**

Rodar o Docker Compose:

```bash
docker-compose up -d
```

- `-d` ou `--detach`: Modo desanexado: execute os contêineres em segundo plano, imprima novos nomes de contêineres.

Verificar status

```bash
docker-compose ps
```

Derrubar compose

```bash
docker-compose down
```

<br>

### **Opção 2 - Desenvolvimento - Apenas os recursos**

Se você deseja apenas levantar o banco de dado, para rodar o `backend` e `frontend` separadamente execute.

```bash
docker-compose -f docker-compose_only-resources.yml up -d
```

Derrubar/baixar containers

```bash
docker-compose down
```

<br>

### **Opção 3 - Criação manual**

Criar rede para contexto company

```bash
docker network create project105_net
```

#### employee-service

Acessar o terminal (em modo administrador) na raiz do `employee-service`

```bash
mvn package -DskipTests
```

Gerar imagem Docker, usando o comando:

```
docker build -t project105/employee .
```

Em seguida, execute o contêiner usando:

```

docker run -i --rm -P --network project105_net project105/employee

docker run -i --rm -p 8081:8080 --network project105_net project105/employee

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
