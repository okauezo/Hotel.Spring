#  Hotel Palazzo — Sistema de Reservas

Este projeto tem como objetivo desenvolver um sistema de **check-in** e **check-out** de um hotel utilizando **Spring Boot**, com operações de **CRUD**, DTOs e integração com banco de dados em memória **H2**.  
A solução também prevê validações de disponibilidade e status de reservas, oferecendo uma base sólida para um sistema de gestão hoteleira.

---

##  Integrantes

| Nome                  | RM        |
|-------------------------|-----------|
| Fabricio Bettarello      | 554638    |
| Eric Yuji                | 554869    |
| Kaue Pires               | 554403    |
| Higor Batista            | 558907    |
| Ricardo Di Tilia         | 555155    |
| Enzo Miletta             | 98677     |

---

## 🧰 Tecnologias Utilizadas

- ☕ **Java 21**
- 🌱 **Spring Boot 3**
- 🧭 Spring Data JPA
- 🛡️ Bean Validation (Jakarta)
- 🧪 Spring Boot Test
- 🧰 Banco de dados **H2** (em memória)
- 📄 Maven

---

## 📁 Estrutura do Projeto

src
├─ main
│ ├─ java
│ │ └─ br.com.fiap.hotel
│ │ ├─ controller
│ │ ├─ domain
│ │ ├─ dto
│ │ ├─ exception
│ │ ├─ repository
│ │ ├─ service
│ │ └─ Application.java
│ └─ resources
│ ├─ static
│ ├─ templates
│ ├─ application.properties
│ └─ data.sql
└─ test
└─ java


---

##  Como Executar

### Pré-requisitos
- JDK 21 instalado
- Maven configurado
- IntelliJ IDEA ou VS Code

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU_USUARIO/hotel-palazzo.git

cd hotel-palazzo

./mvnw spring-boot:run    # Linux/Mac
.\mvnw.cmd spring-boot:run # Windows

Acesse no navegador:

API: http://localhost:8080

Console H2: http://localhost:8080/h2-console


Endpoints Principais
Hóspedes (Guests)

POST /api/guests

GET /api/guests

GET /api/guests/{id}

PUT /api/guests/{id}

DELETE /api/guests/{id}

Quartos (Rooms)

POST /api/rooms

GET /api/rooms

GET /api/rooms/{id}

PUT /api/rooms/{id}

DELETE /api/rooms/{id}

Reservas (Reservations)

POST /api/reservations

GET /api/reservations

GET /api/reservations/{id}

POST /api/reservations/{id}/check-in

POST /api/reservations/{id}/check-out

POST /api/reservations/{id}/cancel


Testes
./mvnw test


Projeto acadêmico — FIAP .
Uso educacional. Todos os direitos reservados aos autores.
