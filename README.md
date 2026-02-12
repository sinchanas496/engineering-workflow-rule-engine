# engineering-workflow-rule-engine

A production-ready full-stack **Rule-Based Engineering Workflow Validation System** built with Spring Boot + MySQL and a clean HTML/CSS/JS dashboard.

## Project Overview
This system validates task status transitions through configurable workflow rules stored in the database. Admins manage employees and rules; engineers manage tasks. Rule violations are blocked, logged, and exposed through APIs.

## Architecture
Layered architecture with MVC and DTO boundaries:

- **controller**: REST API entry points
- **service**: business logic and orchestration
- **repository**: persistence with Spring Data JPA
- **model**: JPA entities and enums
- **dto**: request/response contracts
- **rule**: strategy-based rule engine evaluators
- **exception**: centralized exception handling
- **config**: CORS/web setup
- **util**: mapping helpers

### Rule Engine Design
Implemented via **Strategy pattern**:
- `RuleEvaluator` interface and per-rule implementations
- Database-driven `Rule` entities define behavior per transition
- `RuleEngineService` executes active rules in configured order
- Violations create `ViolationLog` records and raise descriptive exceptions

## Tech Stack
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Lombok
- SLF4J logging
- HTML5, CSS3, JavaScript (Fetch API)

## How to Run Backend
1. Ensure MySQL is running.
2. Create DB and seed data:
   ```bash
   mysql -u root -p < sql/schema.sql
   mysql -u root -p < sql/sample-data.sql
   ```
3. Update DB credentials in `src/main/resources/application.properties`.
4. Run app:
   ```bash
   mvn spring-boot:run
   ```
5. API base URL: `http://localhost:8080/api`

## How to Run Frontend
Frontend files are served by Spring Boot static resources:
- Open `http://localhost:8080/index.html`

## API List
### Employee APIs
- `POST /api/employees`
- `GET /api/employees`
- `GET /api/employees/{id}`
- `PUT /api/employees/{id}`
- `DELETE /api/employees/{id}`

### Task APIs
- `POST /api/tasks`
- `GET /api/tasks`
- `GET /api/tasks/{id}`
- `PUT /api/tasks/{id}`
- `PATCH /api/tasks/{id}/status`
- `DELETE /api/tasks/{id}`

### Rule APIs
- `POST /api/rules`
- `GET /api/rules`
- `GET /api/rules/{id}`
- `PUT /api/rules/{id}`
- `DELETE /api/rules/{id}`

### Violation/Audit APIs
- `GET /api/violations`
- `GET /api/violations?taskId={taskId}`

### Dashboard API
- `GET /api/dashboard`

## Postman
Import collection from: `postman/engineering-workflow-rule-engine.postman_collection.json`
