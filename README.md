# Spring Boot JWT Authentication

A Spring Boot REST API implementing JWT-based authentication and authorization with MySQL for user persistence.

## Features
- User registration and login with JWT token generation
- Protected endpoints requiring Bearer token authentication
- MySQL database for storing user data (username, password, email, firstName, lastName, role)
- Token expiration set to 24 hours

## Prerequisites
- Java 21
- Maven
- MySQL (running on `localhost:3306`)

## Setup
1. **Clone the Repository**
   ```bash
   git clone https://github.com/AbdullahSaimun/security-springboot-jwt.git
   cd jwt
   ```

   2. **Configure MySQL**
       - Create a MySQL database named `jwt_db`.
         - Update `src/main/resources/application.yml` with your MySQL credentials:
           ```yaml
           spring:
                datasource:
                    url: jdbc:mysql://localhost:3306/jwt_demo?createDatabaseIfNotExist=true
                    username: root
                    password: saimun
                    driver-class-name: com.mysql.cj.jdbc.Driver
                jpa:
                    hibernate:
                      ddl-auto: update
                    show-sql: true
                    properties:
                        hibernate:
                          format_sql: true
           ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints
- **Register User**: `POST /api/auth/register`
    - Request Body:
      ```json
      {
          "username": "newuser",
          "password": "newpassword123",
          "email": "newuser@example.com",
          "firstName": "New",
          "lastName": "User",
          "role": "ROLE_USER"
      }
      ```
    - Response: `User registered successfully`

- **Login**: `POST /api/auth/login`
    - Request Body:
      ```json
      {
          "username": "newuser",
          "password": "newpassword123"
      }
      ```
    - Response:
      ```json
      {
          "token": "<jwt-token>"
      }
      ```

- **Protected Endpoint**: `GET /api/test/protected`
    - Header: `Authorization: Bearer <jwt-token>`
    - Response: `This is a protected endpoint!`

## Testing
1. **Register a User**:
   Use Postman or cURL to send a POST request to `http://localhost:8080/api/auth/register`.

2. **Login**:
   Send a POST request to `http://localhost:8080/api/auth/login` to obtain a JWT token.

3. **Access Protected Endpoint**:
   Send a GET request to `http://localhost:8080/api/test/protected` with the `Authorization` header including the Bearer token.

   Example with cURL:
   ```bash
   curl -H "Authorization: Bearer <jwt-token>" http://localhost:8080/api/test/protected
   ```

## Notes
- The JWT token expires after 24 hours. Obtain a new token via `/api/auth/login` if expired.
- Store the `jwt.secret` securely in production (e.g., environment variables).
- Sample users (`admin`/`admin123`, `user`/`user123`) are added on startup via `DataInitializer`.

## Dependencies
- Spring Boot 3.3.4
- Spring Security
- MySQL Connector
- JJWT (JSON Web Token)
- Lombok