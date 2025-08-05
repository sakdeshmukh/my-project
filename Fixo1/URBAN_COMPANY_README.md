# Urban Company-like Service Platform

A comprehensive Spring Boot application that mimics Urban Company's functionality, providing a platform for customers to book services from verified service providers.

## 🚀 Features

### Core Functionality
- **User Authentication & Authorization**: JWT-based authentication with role-based access control
- **User Registration**: Support for Customer, Service Provider, and Admin roles
- **Service Management**: CRUD operations for services and categories
- **Booking System**: Complete booking lifecycle management
- **Payment Processing**: Payment creation and processing
- **Review & Rating System**: Customer feedback and service provider ratings
- **Shopping Cart**: Add/remove services to cart before booking
- **Admin Dashboard**: Comprehensive admin management features

### User Roles
1. **Customer**: Book services, manage bookings, provide feedback
2. **Service Provider**: Manage services, view bookings, update profile
3. **Admin**: Manage users, services, categories, and platform operations

## 🛠 Technology Stack

- **Backend**: Java 11, Spring Boot 2.7.18
- **Database**: MySQL
- **Security**: Spring Security with JWT
- **Documentation**: Swagger/OpenAPI 3
- **Build Tool**: Maven
- **Lombok**: For reducing boilerplate code
- **ModelMapper**: For object mapping

## 📋 Prerequisites

- Java 11 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## 🚀 Quick Start

### 1. Database Setup
```sql
CREATE DATABASE fixo_db;
```

### 2. Configuration
Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 4. Access Swagger Documentation
Visit `http://localhost:8080/swagger-ui/index.html` for API documentation

## 📚 API Endpoints

### Authentication (`/api/auth`)
- `POST /signup` - Register a new user
- `POST /signup/customer` - Register as customer
- `POST /signup/service-provider` - Register as service provider
- `POST /signin` - Sign in user
- `POST /signin/customer` - Sign in as customer
- `POST /signin/service-provider` - Sign in as service provider
- `POST /signin/admin` - Sign in as admin

### Customer Operations (`/api/customer`)
- `GET /profile` - Get customer profile
- `PUT /profile` - Update customer profile
- `GET /bookings` - Get customer bookings
- `POST /book-service` - Book a service

### Service Provider Operations (`/api/service-provider`)
- `GET /profile` - Get service provider profile
- `PUT /profile` - Update service provider profile
- `GET /bookings` - Get service provider bookings
- `GET /services` - Get provider's services

### Admin Operations (`/api/admin`)
- `GET /customers` - Get all customers
- `GET /service-providers` - Get all service providers
- `GET /services` - Get all services
- `GET /bookings` - Get all bookings
- `GET /payments` - Get all payments

### Service Management (`/api/services`)
- `GET /` - Get all services
- `GET /{id}` - Get service by ID
- `GET /category/{categoryId}` - Get services by category

### Booking Management (`/api/bookings`)
- `GET /` - Get all bookings (Admin)
- `GET /my-bookings` - Get user's bookings
- `GET /{bookingId}` - Get booking by ID
- `POST /create` - Create new booking
- `GET /by-date` - Get bookings by date
- `DELETE /{bookingId}` - Cancel booking

### Payment Management (`/api/payments`)
- `GET /` - Get all payments (Admin)
- `GET /{paymentId}` - Get payment by ID
- `POST /create` - Create new payment
- `POST /{paymentId}/process` - Process payment
- `GET /by-status` - Get payments by status
- `GET /by-amount` - Get payments by amount
- `DELETE /{paymentId}` - Cancel payment

### Feedback Management (`/api/feedback`)
- `GET /` - Get all feedback (Admin)
- `GET /{feedbackId}` - Get feedback by ID
- `POST /create` - Create new feedback
- `GET /service-provider/{serviceProviderId}` - Get feedback for service provider
- `GET /by-rating` - Get feedback by rating
- `DELETE /{feedbackId}` - Delete feedback

### Cart Management (`/api/cart`)
- `GET /` - Get user's cart
- `GET /items` - Get cart items
- `POST /add` - Add item to cart
- `PUT /update` - Update cart item quantity
- `DELETE /remove/{cartItemId}` - Remove item from cart
- `DELETE /clear` - Clear cart

## 🏗 Architecture

### Controller Layer
- RESTful controllers with proper HTTP methods
- Input validation using Bean Validation
- Swagger documentation for all endpoints
- Role-based access control

### Service Layer
- Business logic implementation
- Transaction management
- Data validation and processing
- Integration between different components

### Repository Layer
- Data access layer using Spring Data JPA
- Custom query methods
- Database operations

### Entity Layer
- JPA entities with proper relationships
- Validation annotations
- Lombok for reducing boilerplate

### Security Layer
- JWT-based authentication
- Role-based authorization
- Password encryption using BCrypt
- Custom user details service

## 🔐 Security Features

- **JWT Authentication**: Stateless authentication using JSON Web Tokens
- **Role-based Access Control**: Different permissions for different user roles
- **Password Encryption**: BCrypt password hashing
- **Input Validation**: Bean Validation for request validation
- **CORS Configuration**: Cross-origin resource sharing setup

## 📊 Database Schema

### Core Entities
- **User**: Base user information and authentication
- **Customer**: Customer-specific information
- **ServiceProvider**: Service provider details and verification
- **Service**: Service offerings with pricing
- **Category**: Service categories
- **Booking**: Service booking information
- **Payment**: Payment transactions
- **Feedback**: Customer reviews and ratings
- **Cart**: Shopping cart functionality
- **CartItem**: Individual items in cart

### Key Relationships
- Customer/ServiceProvider extends User (One-to-One)
- Service belongs to Category (Many-to-One)
- ServiceProvider offers Service (Many-to-One)
- Booking belongs to Customer and Cart (Many-to-One, One-to-One)
- Payment belongs to Booking (One-to-One)
- Feedback belongs to ServiceProvider (Many-to-One)
- Cart belongs to Customer (One-to-One)
- CartItem belongs to Cart and ServiceProvider (Many-to-One)

## 🧪 Testing

The application includes comprehensive test coverage:
- Unit tests for services
- Integration tests for controllers
- Repository tests for data access

Run tests using:
```bash
mvn test
```

## 📝 Sample API Usage

### 1. Register as Customer
```bash
curl -X POST "http://localhost:8080/api/auth/signup/customer" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "password": "password123",
    "phoneNo": "1234567890",
    "address": "123 Main St, City"
  }'
```

### 2. Sign In
```bash
curl -X POST "http://localhost:8080/api/auth/signin" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

### 3. Add Service to Cart
```bash
curl -X POST "http://localhost:8080/api/cart/add?serviceProviderId=1&quantity=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

### 4. Create Booking
```bash
curl -X POST "http://localhost:8080/api/bookings/create" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "cartId": 1,
    "specialInstructions": "Please arrive on time"
  }'
```

## 🔧 Configuration

### Application Properties
- Database configuration
- JWT secret and expiration
- Server port and context path
- JPA/Hibernate settings

### Security Configuration
- Public endpoints (auth, swagger)
- Role-based endpoint protection
- JWT filter configuration
- Password encoder setup

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/projj1-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:11-jre-slim
COPY target/projj1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the Swagger documentation at `/swagger-ui/index.html`
- Review the application logs for debugging

---

**Note**: This is a demonstration application. For production use, additional security measures, error handling, and performance optimizations should be implemented. 