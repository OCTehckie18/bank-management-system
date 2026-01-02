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

## Phase 4 – Persistence Layer (Database-Backed Core)

### What was implemented

- Introduced relational persistence using Spring Data JPA
- Designed database schema for:
  - Accounts
  - Transactions (append-only ledger)
  - Loan requests
- Added Flyway-ready entity mappings
- Implemented repositories for all aggregates
- Replaced in-memory storage with DB-backed services
- Introduced clean mappers (Domain ↔ Entity)
- Enforced UUID-based identity across service layer
- Aligned REST controllers to UUID boundaries
- Introduced DTO-style `TransactionView` to avoid leaking JPA entities
- Persistence layer designed to be database-agnostic (tested with in-memory DB)

### Architectural highlights

- Core domain remains persistence-agnostic
- `core-engine` has **zero dependency** on JPA or Spring
- `bank-api` adapts persistence to domain via mappers
- Controllers expose DTOs, not entities
- Strict module boundaries enforced by compiler

### Status

- Core banking flows (create, deposit, withdraw, transfer) are fully DB-backed
- Loan processing and undo functionality are stubbed intentionally
  and scheduled for implementation in Phase 5/6

---

## Technology Stack

- Java 21
- Spring Boot 3.3.x
- Maven
- Swagger / OpenAPI
- Java collections (Stack, Queue) for algorithmic workflows (Phase 2/3)

---

## Project Structure

bank-management-system/
├── core-engine/ # Pure Java domain & business logic
├── bank-api/ # Spring Boot REST API
└── README.md

---

## Upcoming Phases

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
# Build core engine (pure Java)
cd core-engine
mvn clean install

# Run Spring Boot API
cd ../bank-api
mvn spring-boot:run

```

---

## Configuration & Git Hygiene

This project follows standard Git and configuration best practices to ensure
security and clean version control.

### Environment Variables

- Database credentials and environment-specific configuration are **not hardcoded**
- Sensitive values are expected to be supplied via environment variables
- Example configuration is referenced in `application.properties` using placeholders

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```
