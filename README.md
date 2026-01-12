# 🧠 MergeMind

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Security-black?style=for-the-badge&logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Swagger-API%20Docs-85EA2D?style=for-the-badge&logo=swagger)

**A marketplace for developers to network, register their projects, and find collaborators, creating a community where developers can meet and work together, simulating a real job market environment.**

[API Documentation](#-api-documentation) •
[Installation](#-installation) •
[Endpoints](#-main-endpoints) •
[Technologies](#-technologies)

</div>

---

## 📋 Table of Contents

- [About the Project](#-about-the-project)
- [Features](#-features)
- [Technologies](#-technologies)
- [Architecture](#-architecture)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Main Endpoints](#-main-endpoints)
- [Security](#-security)
- [Data Model](#-data-model)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🎯 About the Project

**MergeMind** is an innovative platform that connects developers to projects and collaboration opportunities. It works as a marketplace where: 

- 👨‍💻 Developers can create profiles with their technology stacks
- 🚀 Project leaders can register their projects and job openings (roles)
- 📝 Developers can apply to project positions
- 🤝 Facilitates networking and real-time collaboration

The system simulates a real job market environment, allowing developers to gain practical experience while contributing to real projects.

---

## ✨ Features

### Authentication and Authorization
- ✅ User registration with data validation
- ✅ Login with JWT authentication
- ✅ Tokens with configurable expiration (2 hours)
- ✅ Route protection with Spring Security
- ✅ Secure password updates

### User Management
- ✅ Profile creation and updates
- ✅ Technology stack management
- ✅ GitHub and profile photo URLs
- ✅ Personalized biography

### Project Management
- ✅ Complete CRUD operations for projects
- ✅ Dynamic filters (title, status)
- ✅ Pagination and sorting
- ✅ Project status (OPEN, CLOSED, IN_PROGRESS)
- ✅ Customizable banner
- ✅ Participant management

### Role Management (Job Positions)
- ✅ Role creation within projects
- ✅ Required stack definitions
- ✅ Detailed position descriptions
- ✅ Role updates and removal

### Application System
- ✅ Apply to project positions
- ✅ Status management (PENDING, APPROVED, REJECTED)
- ✅ Query applications by user
- ✅ Query applications by project
- ✅ Application status updates

### API Documentation
- ✅ Automatic documentation with Swagger/OpenAPI
- ✅ Interactive interface for endpoint testing
- ✅ Documented schemas and models

---

## 🛠 Technologies

### Backend
- **Java 21** - Programming language
- **Spring Boot 4.0.1** - Main framework
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication and authorization
- **Spring Validation** - Data validation

### Database
- **PostgreSQL** - Relational database
- **Hibernate** - ORM (Object-Relational Mapping)

### Security
- **JWT (JSON Web Tokens)** - Stateless authentication
- **Auth0 Java-JWT 4.4.0** - JWT implementation
- **BCrypt** - Password encryption

### Documentation
- **SpringDoc OpenAPI 2.8. 14** - Automatic API documentation
- **Swagger UI** - Visual documentation interface

### Tools
- **Lombok** - Boilerplate reduction
- **Maven** - Dependency management
- **Maven Wrapper** - Build portability

---

## 🏗 Architecture

The project follows a layered architecture based on **Clean Architecture** and **DDD (Domain-Driven Design)** principles:

```
├── controllers/     # Presentation layer (REST API)
├── services/        # Business logic layer
├── repository/      # Persistence layer (JPA)
├── domain/          # Domain entities
├── dto/             # Data Transfer Objects
│   ├── request/     # Input DTOs
│   ├── response/    # Output DTOs
│   └── mapper/      # Converters between entities and DTOs
├── exceptions/      # Exception handling
└── config/          # System configurations
```

### Design Patterns Used
- **DTO Pattern** - Data transfer between layers
- **Repository Pattern** - Data layer abstraction
- **Service Layer Pattern** - Isolated business logic
- **Dependency Injection** - Inversion of control
- **Exception Handler** - Centralized error handling

---

## 📦 Installation

### Prerequisites
- Java 21 or higher
- PostgreSQL 12+
- Maven 3.8+ (or use the included Maven Wrapper)
- Git

### Step by Step

1. **Clone the repository**
```bash
git clone https://github.com/Durannd/MergeMind.git
cd MergeMind
```

2. **Configure PostgreSQL database**
```sql
CREATE DATABASE mergemind;
CREATE USER mergemind_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE mergemind TO mergemind_user;
```

3. **Configure environment variables**

Create a `.env` file or configure environment variables: 
```bash
# Database
export db. url=jdbc:postgresql://localhost:5432/mergemind
export db.username=mergemind_user
export db.password=your_password

# JWT
export JWT_SECRET=your-super-secret-jwt-key-change-this-in-production
```

4. **Build the project**
```bash
# Using installed Maven
mvn clean install

# Or using Maven Wrapper
./mvnw clean install
```

5. **Run the application**
```bash
# Using Maven
mvn spring-boot:run

# Or using Maven Wrapper
./mvnw spring-boot:run

# Or running the JAR
java -jar target/MergeMind-0.0.1-SNAPSHOT.jar
```

6. **Access the application**
- API:  `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## ⚙️ Configuration

### application.properties

```properties
# Application Name
spring.application.name=MergeMind

# Database Configuration
spring.datasource.url=${db.url}
spring.datasource.username=${db.username}
spring.datasource.password=${db.password}
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Security
api.security.token.secret=${JWT_SECRET}

# Pagination
spring.data.web.pageable.default-page-size=20
spring. data.web.pageable.max-page-size=100
```

### Security Settings

- **Token Expiration**: 2 hours
- **Token Issuer**: mergemind-api
- **Timezone**: GMT-3 (America/Sao_Paulo)
- **Algorithm**:  HMAC256

---

## 📚 API Documentation

### Swagger/OpenAPI

The complete and interactive API documentation is available through **Swagger UI**. 

**URL**:  `http://localhost:8080/swagger-ui/index.html`

Through Swagger you can:
- ✅ View all available endpoints
- ✅ Test requests directly through the interface
- ✅ See request/response schemas
- �� Check HTTP status codes
- ✅ Export OpenAPI specification

### OpenAPI Specification

**URL**: `http://localhost:8080/v3/api-docs`

---

## 🔌 Main Endpoints

### 🔐 Authentication

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | User login | No |

**Request Example - Register:**
```json
{
  "name": "John Doe",
  "email": "john@example. com",
  "password": "password123",
  "bio": "Full Stack Developer",
  "github_url": "https://github.com/johndoe",
  "stacks": ["JAVA", "SPRING", "REACT"]
}
```

**Response Example:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user":  {
    "id": 1,
    "name": "John Doe",
    "email":  "john@example.com",
    "bio": "Full Stack Developer",
    "github_url":  "https://github.com/johndoe",
    "stacks": ["JAVA", "SPRING", "REACT"]
  }
}
```

### 👤 Users

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| GET | `/api/users/{id}` | Get user by ID | Yes |
| PATCH | `/api/users/{id}` | Update user | Yes |
| PATCH | `/api/users/{id}/password` | Update password | Yes |

### 🚀 Projects

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/projects` | Create project | Yes |
| GET | `/api/projects` | List all (paginated) | Yes |
| GET | `/api/projects/{id}` | Get by ID | Yes |
| GET | `/api/projects/filters` | Filter by title/status | Yes |
| GET | `/api/projects/by-title? title={title}` | Search by title | Yes |
| GET | `/api/projects/by-status?status={status}` | Search by status | Yes |
| GET | `/api/projects/{id}/roles` | List project roles | Yes |
| PATCH | `/api/projects/{id}` | Update project | Yes |
| DELETE | `/api/projects/{id}` | Delete project | Yes |

**Request Example - Create Project:**
```json
{
  "title": "E-commerce System",
  "description": "Complete e-commerce platform with microservices",
  "banner_url": "https://example.com/banner.jpg",
  "status": "OPEN",
  "user":  {
    "id": 1
  }
}
```

### 💼 Roles (Job Positions)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| PATCH | `/api/roles/{id}` | Update role | Yes |
| DELETE | `/api/roles/{id}` | Delete role | Yes |

### 📝 Applications

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/api/applications` | Create application | Yes |
| GET | `/api/applications/user/{id}` | User applications | Yes |
| GET | `/api/applications/project/{id}` | Project applications | Yes |
| PATCH | `/api/applications/{id}` | Update status | Yes |
| DELETE | `/api/applications/{id}` | Delete application | Yes |

**Request Example - Create Application:**
```json
{
  "user":  {
    "id": 1
  },
  "role":  {
    "id": 5
  },
  "status":  "PENDING"
}
```

### 📄 Pagination

All listing endpoints support pagination:

**Query Parameters:**
- `page` - Page number (default: 0)
- `size` - Page size (default: 20, max: 100)
- `sort` - Field for sorting
- `direction` - Direction (ASC/DESC)

**Example:**
```
GET /api/projects?page=0&size=10&sort=title&direction=ASC
```

---

## 🔒 Security

### JWT Authentication

The system uses **JSON Web Tokens (JWT)** for stateless authentication.

**Authentication Flow:**

1. Client logs in with email/password
2. Server validates credentials
3. Server generates JWT token
4. Client stores token
5. Client sends token in each request via `Authorization:  Bearer {token}` header
6. Server validates token and processes request

### Security Configuration

```java
// Public endpoints
/api/auth/login
/api/auth/register
/swagger-ui/**
/v3/api-docs/**

// All other endpoints require authentication
```

### Password Encoding

- Passwords are encrypted using **BCrypt**
- No password is stored in plain text
- Hash is generated with random salt

### Exception Handling

The system has centralized exception handling:

- `EmailInUseException` - 409 CONFLICT
- `ResourceNotFoundException` - 404 NOT FOUND
- `PasswordIncorrectException` - 401 UNAUTHORIZED
- `NullPasswordException` - 400 BAD REQUEST
- `EntityNotFoundException` - 404 NOT FOUND
- `IllegalArgumentException` - 400 BAD REQUEST
- `RuntimeException` - 500 INTERNAL SERVER ERROR

---

## 💾 Data Model

### ER Diagram (Entity-Relationship)

```
┌─────────────────┐         ┌─────────────────┐
│      User       │         │     Project     │
├─────────────────┤         ├─────────────────┤
│ PK  id          │────┐    │ PK  id          │
│     name        │    │    │     title       │
│     email       │    │    │     description │
│     password    │    │    │     banner_url  │
│     bio         │    │    │     status      │
│     github_url  │    └───>│ FK  user_id     │
│     photo_url   │         └─────────────────┘
└─────────────────┘                 │
        │                           │
        │                           │
        │                   ┌───────┴────────┐
        │                   │      Role      │
        │                   ├────────────────┤
        │                   │ PK  id         │
        │                   │     name       │
        │                   │     description│
        │                   │ FK  project_id │
        │                   └────────┬───────┘
        │                            │
        │                            │
        └────────┐          ┌────────┘
                 │          │
          ┌──────┴──────────┴───────┐
          │     Application         │
          ├─────────────────────────┤
          │ PK  id                  │
          │     status              ��
          │ FK  user_id             │
          │ FK  role_id             │
          └─────────────────────────┘
```

### Main Entities

#### User
```java
- id:  Long (PK)
- name: String
- email: String (unique)
- password: String (encrypted)
- bio: String
- github_url: String
- photo_url: String
- stacks: List<Stacks>
```

#### Project
```java
- id: Long (PK)
- title: String
- description:  String
- banner_url: String
- status: Status (OPEN, CLOSED, IN_PROGRESS)
- user:  User (FK - owner)
- participants: List<User>
```

#### Role
```java
- id: Long (PK)
- name: String
- description:  String
- project:  Project (FK)
- stacks: List<Stacks>
```

#### Application
```java
- id: Long (PK)
- status: Status (PENDING, APPROVED, REJECTED)
- user: User (FK)
- role: Role (FK)
```

### Enums

**Status:**
- `OPEN` - Open
- `CLOSED` - Closed
- `IN_PROGRESS` - In Progress
- `PENDING` - Pending
- `APPROVED` - Approved
- `REJECTED` - Rejected

**Stacks:**
Enum with major technologies (Java, Spring, React, Angular, Node.js, etc.)

---

## 📁 Project Structure

```
MergeMind/
├── src/
│   ├── main/
│   │   ├── java/com/ricael/mergemind/
│   │   │   ├── controllers/          # REST Controllers
│   │   │   │   ├── AuthController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── ProjectController.java
│   │   │   │   ├── RoleController. java
│   │   │   │   └── ApplicationController.java
│   │   │   ├── services/             # Business Logic
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── UserServices.java
│   │   │   │   ├── ProjectServices.java
│   │   │   │   ├── RoleServices.java
│   │   │   │   ├── ApplicationServices.java
│   │   │   │   └── TokenService.java
│   │   │   ├── repository/           # Data Access Layer
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── ProjectRepository.java
│   │   │   │   ├── RoleRepository. java
│   │   │   │   └── ApplicationRepository.java
│   │   │   ├── domain/               # Domain Entities
│   │   │   │   ├── User.java
│   │   │   │   ├── Project.java
│   │   │   │   ├── Role.java
│   │   │   │   ├── Application.java
│   │   │   │   └── enums/
│   │   │   │       ├── Status. java
│   │   │   │       └── Stacks. java
│   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   │   ├── request/
│   │   │   │   ├── response/
│   │   │   │   └── mapper/
│   │   │   ├── exceptions/           # Custom Exceptions
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   └── config/               # Configurations
│   │   │       └── SecurityConfig.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── . mvn/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── SPRING_SECURITY_JWT_GUIDE.md
└── README.md
```

---

## 🤝 Contributing

Contributions are always welcome! To contribute:

1. Fork the project
2. Create a branch for your feature (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add:  amazing new feature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Commit Standards

- `Add: ` - New feature
- `Fix:` - Bug fix
- `Update:` - Feature update
- `Remove:` - Code removal
- `Docs:` - Documentation changes
- `Refactor:` - Code refactoring
- `Test:` - Test addition or modification

---

## 📝 License

This project is under the MIT license.  See the [LICENSE](LICENSE) file for more details.

---

## 👨‍💻 Author

**Durannd**

- GitHub: [@Durannd](https://github.com/Durannd)
- Repository: [MergeMind](https://github.com/Durannd/MergeMind)

---

## 📞 Support

If you have any questions or suggestions, feel free to:

- Open an [issue](https://github.com/Durannd/MergeMind/issues)
- Submit a [pull request](https://github.com/Durannd/MergeMind/pulls)

---

<div align="center">

**⭐ If this project was useful, consider giving it a star! **

Made with ❤️ and ☕ by Durannd

</div>