# CodeMentorAI

CodeMentorAI is a backend application built using Java and Spring Boot to manage user information through REST APIs. It integrates PostgreSQL for data persistence and Redis for caching.

## Features

- RESTful APIs for user management
- Create, retrieve, update, and delete users
- Spring Data JPA and Hibernate integration
- PostgreSQL database integration
- Spring Security configuration
- Redis caching for user data
- Cache updates and eviction
- DTO-based request and response handling
- Exception handling for missing users and duplicate emails

## Technology Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- PostgreSQL
- Redis
- Maven
- JUnit / Spring Boot Test

## Prerequisites

- Java version compatible with `pom.xml`
- PostgreSQL
- Redis
- Maven (or use the included Maven Wrapper)

## Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/Priyan09ka/CodeMentorAI.git
cd CodeMentorAI
```

### 2. Configure the application

Configure your local PostgreSQL database and Redis connection in your local `application.properties` file. Do not commit passwords or secret keys to GitHub.

### 3. Start the application

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Project Structure

```text
src/main/java/com/codementor/ai/
├── config/
├── controller/
├── dto/
├── entity/
├── exception/
├── repository/
└── service/
```

## Future Improvements

- Expand automated test coverage
- Add API documentation
- Integrate a frontend
- Improve deployment configuration

## Author

Java Backend Development Project
