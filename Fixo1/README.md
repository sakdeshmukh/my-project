# Spring Boot Authentication System with JWT

This project implements a complete authentication system using Spring Boot, Spring Security, and JWT tokens.

## Features

- **User Registration**: Sign up with email, password, phone number, and role
- **User Login**: Sign in with email and password
- **JWT Token Authentication**: Stateless authentication using JWT tokens
- **Role-based Authorization**: Support for CUSTOMER, ADMIN, and SERVICEPROVIDER roles
- **Password Encryption**: BCrypt password hashing
- **Input Validation**: Comprehensive validation for all inputs
- **Error Handling**: Global exception handling with proper error responses

## Project Structure

```
src/main/java/com/cdac/
├── controller/
│   ├── AuthController.java          # Authentication endpoints
│   ├── TestController.java          # Test endpoints for demonstration
│   └── GlobalExceptionHandler.java  # Global error handling
├── dao/
│   └── UserRepository.java          # User data access layer
├── dto/
│   ├── SignUpRequest.java          # Sign up request DTO
│   ├── SignInRequest.java          # Sign in request DTO
│   └── AuthResponse.java           # Authentication response DTO
├── entities/
│   ├── User.java                   # User entity
│   ├── UserRole.java               # User role enum
│   └── BaseEntity.java             # Base entity with common fields
├── security/
│   ├── SecurityConfig.java         # Spring Security configuration
│   ├── JwtUtil.java               # JWT utility functions
│   ├── JwtAuthenticationFilter.java # JWT authentication filter
│   └── CustomUserDetailsService.java # Custom user details service
└── service/
    └── AuthService.java            # Authentication business logic
```

## API Endpoints

### Authentication Endpoints

#### 1. Sign Up
- **URL**: `POST /api/auth/signup`
- **Description**: Register a new user
- **Request Body**:
```json
{
    "email": "user@example.com",
    "password": "password123",
    "phoneNo": "1234567890",
    "userRole": "CUSTOMER"
}
```
- **Response**:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "email": "user@example.com",
    "userRole": "CUSTOMER",
    "message": "Authentication successful",
    "success": true
}
```

#### 2. Sign In
- **URL**: `POST /api/auth/signin`
- **Description**: Authenticate existing user
- **Request Body**:
```json
{
    "email": "user@example.com",
    "password": "password123"
}
```
- **Response**:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "email": "user@example.com",
    "userRole": "CUSTOMER",
    "message": "Authentication successful",
    "success": true
}
```

### Test Endpoints

#### 1. Public Endpoint
- **URL**: `GET /api/test/public`
- **Description**: No authentication required
- **Headers**: None required

#### 2. Protected Endpoint
- **URL**: `GET /api/test/protected`
- **Description**: Requires valid JWT token
- **Headers**: `Authorization: Bearer <jwt_token>`

#### 3. Admin Endpoint
- **URL**: `GET /api/test/admin`
- **Description**: Requires ADMIN role
- **Headers**: `Authorization: Bearer <jwt_token>`

## Configuration

### Database Configuration
Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### JWT Configuration
```properties
jwt.secret=your_jwt_secret_key_here
jwt.expiration=86400000
```

## Security Features

1. **Password Encryption**: All passwords are encrypted using BCrypt
2. **JWT Token**: Stateless authentication with configurable expiration
3. **Role-based Access**: Different endpoints can be restricted by user roles
4. **Input Validation**: Comprehensive validation for all inputs
5. **CSRF Protection**: Disabled for REST API (can be enabled if needed)
6. **Session Management**: Stateless sessions using JWT

## Usage Examples

### 1. Register a New User
```bash
curl -X POST http://localhost:8080/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123",
    "phoneNo": "1234567890",
    "userRole": "CUSTOMER"
  }'
```

### 2. Login User
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 3. Access Protected Endpoint
```bash
curl -X GET http://localhost:8080/api/test/protected \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..."
```

## Error Handling

The application provides comprehensive error handling:

- **Validation Errors**: Detailed field-level validation errors
- **Authentication Errors**: Clear messages for invalid credentials
- **Duplicate Registration**: Prevents duplicate email/phone registration
- **Global Exception Handler**: Consistent error response format

## Dependencies

- Spring Boot 3.5.3
- Spring Security
- Spring Data JPA
- MySQL Connector
- JWT (jjwt)
- Lombok
- Validation
- Swagger/OpenAPI

## Running the Application

1. **Start MySQL Database**
2. **Update application.properties** with your database credentials
3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
4. **Access Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

## Testing

The application includes test endpoints to verify:
- Public access (no authentication)
- Protected access (with JWT token)
- Role-based access (admin only)

Use the provided test endpoints to verify your authentication setup. 