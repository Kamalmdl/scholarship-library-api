# Library Management API

A CRUD REST API for a library management system, built with a layered architecture as part of a scholarship program submission.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Lombok
- Bean Validation (Jakarta Validation)
- springdoc-openapi (Swagger UI)
- JUnit 5 + Mockito

## Architecture

The project follows a classic layered architecture:

- **Entity** — JPA-mapped domain models (`Author`, `Book`, `Member`)
- **DTO** — request/response objects; entities are never exposed directly through the API
- **Repository** — Spring Data JPA interfaces
- **Service** — business logic, exposed through interfaces with `Impl` implementations
- **Controller** — REST endpoints

## Domain

- **Author** — has many **Books** (One-to-Many)
- **Book** — belongs to one **Author**, can be borrowed by many **Members** (Many-to-Many)
- **Member** — can borrow many **Books**

## Features

- Full CRUD for Author, Book, and Member
- DTO-based request/response separation with validation (`@NotBlank`, `@Email`, `@Size`, `@NotNull`)
- Centralized exception handling (`@RestControllerAdvice`) with proper HTTP status codes (200, 201, 204, 400, 404, 500)
- Pagination and sorting on list endpoints (`?page=0&size=10&sort=fullName,asc`)
- Interactive API documentation via Swagger UI
- Unit tests for the service layer (Mockito)

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL running locally

### Configuration

Update `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

### API Documentation

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Endpoints

| Method | Endpoint              | Description         |
|--------|------------------------|----------------------|
| POST   | `/api/authors`         | Create an author     |
| GET    | `/api/authors`         | List authors (paginated) |
| GET    | `/api/authors/{id}`    | Get an author by id  |
| PUT    | `/api/authors/{id}`    | Update an author     |
| DELETE | `/api/authors/{id}`    | Delete an author     |

Book and Member expose the same set of endpoints under `/api/books` and `/api/members`.

## Running Tests

```bash
mvn test
```
