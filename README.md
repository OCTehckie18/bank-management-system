![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.x-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

# Bank Management System (Java + Spring Boot)

A modular, backend-focused Bank Management System built step-by-step using **core Java**, **data structures**, and **Spring Boot**, designed to be learning-oriented and job-ready.

---

## Phase 1 – Core Banking Engine (Pure Java, OOP)

- Designed a **domain-driven core banking engine** using Java OOP principles
- Implemented multiple account types using **inheritance and polymorphism**
- Encapsulated critical business rules:
  - balance validation
  - frozen account checks
- Used **Factory Pattern** for account creation
- Built immutable **Transaction** model with timestamps
- Defined custom domain exceptions for rule enforcement
- Kept the core engine completely **framework-agnostic**
  - no Spring
  - no database
  - pure Java logic

> **Focus:** Object modeling, correctness, and clean business rules

---

## Phase 2 – Advanced Data Structures & Service Layer

- Introduced a **service layer** via `BankOperations` interface
- Enforced **interface-driven design** for loose coupling
- Implemented **undo functionality using Stack (LIFO)**
- Implemented **loan request handling using Queue (FIFO)**
- Added in-memory **transaction history (audit trail)**
- Ensured clear separation between:
  - domain models
  - service workflows
  - data structure logic

> **Focus:** Algorithms, data structures, and service contracts

---

## Phase 3 – Spring Boot REST API

- Exposed the core banking engine via **RESTful APIs**
- Implemented transaction workflows:
  - deposit
  - withdraw
  - transfer
- Integrated **undo functionality (stack-based)**
- Integrated **loan processing (queue-based)**
- Added **global exception handling**
- Integrated **Swagger / OpenAPI documentation**
- Stabilized stack using:
  - Java 21 (LTS)
  - Spring Boot 3.3.x

> **Focus:** Backend architecture, APIs, and developer experience

---

## Technology Stack

- Java 21
- Spring Boot 3.3.x
- Maven
- Swagger / OpenAPI
- In-memory data structures (Stack, Queue, Map)

---

## Project Structure

bank-management-system/
├── core-engine/ # Pure Java domain & business logic
├── bank-api/ # Spring Boot REST API
└── README.md

---

## Upcoming Phases

- **Phase 4 – Database Integration**

  - JPA / Hibernate
  - PostgreSQL or MySQL
  - Persistent audit trail

- **Phase 5 – Security**

  - Spring Security
  - JWT authentication
  - Role-based access control

- **Phase 6 – AI Integration**
  - Fraud detection
  - Risk scoring
  - ML-powered decision support

---

## How to Run

```bash
# Run core engine tests
cd core-engine
mvn clean install

# Run Spring Boot API
cd ../bank-api
mvn spring-boot:run
```
