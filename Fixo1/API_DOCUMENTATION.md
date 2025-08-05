# Urban Company-like Platform API Documentation

## Base URL
```
http://localhost:8080
```

## Authentication
All protected endpoints require a JWT token in the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

## API Endpoints

### 1. Authentication Endpoints

#### Register as Customer
```http
POST /api/auth/signup/customer
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "password123",
  "phoneNo": "1234567890",
  "address": "123 Main St, City"
}
```

#### Register as Service Provider
```http
POST /api/auth/signup/service-provider
Content-Type: application/json

{
  "serviceProviderName": "AC Expert Services",
  "businessAddress": "123 Service Lane",
  "serviceCategory": "Appliance Repair",
  "licenseNumber": "AC001",
  "businessDescription": "Professional AC repair services",
  "yearsOfExp": 5,
  "firstName": "Mike",
  "lastName": "Johnson",
  "email": "mike@acexpert.com",
  "password": "password123",
  "phoneNo": "9876543210"
}
```

#### Sign In
```http
POST /api/auth/signin
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "success": true,
  "message": "Authentication successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userRole": "CUSTOMER"
}
```

### 2. Customer Endpoints

#### Get Customer Profile
```http
GET /api/customer/profile
Authorization: Bearer <token>
```

#### Update Customer Profile
```http
PUT /api/customer/profile
Authorization: Bearer <token>
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "address": "456 New Address, City"
}
```

#### Get Customer Bookings
```http
GET /api/customer/bookings
Authorization: Bearer <token>
```

#### Book a Service
```http
POST /api/customer/book-service?cartId=1
Authorization: Bearer <token>
```

### 3. Service Provider Endpoints

#### Get Service Provider Profile
```http
GET /api/service-provider/profile
Authorization: Bearer <token>
```

#### Update Service Provider Profile
```http
PUT /api/service-provider/profile
Authorization: Bearer <token>
Content-Type: application/json

{
  "serviceProviderName": "Updated AC Expert Services",
  "businessAddress": "456 New Service Lane",
  "businessDescription": "Updated description"
}
```

#### Get Service Provider Bookings
```http
GET /api/service-provider/bookings
Authorization: Bearer <token>
```

### 4. Admin Endpoints

#### Get All Customers
```http
GET /api/admin/customers
Authorization: Bearer <token>
```

#### Get All Service Providers
```http
GET /api/admin/service-providers
Authorization: Bearer <token>
```

#### Get All Services
```http
GET /api/admin/services
Authorization: Bearer <token>
```

#### Get All Bookings
```http
GET /api/admin/bookings
Authorization: Bearer <token>
```

#### Get All Payments
```http
GET /api/admin/payments
Authorization: Bearer <token>
```

### 5. Service Management

#### Get All Services
```http
GET /api/services
Authorization: Bearer <token>
```

#### Get Service by ID
```http
GET /api/services/1
Authorization: Bearer <token>
```

#### Get Services by Category
```http
GET /api/services/category/1
Authorization: Bearer <token>
```

### 6. Booking Management

#### Get All Bookings (Admin)
```http
GET /api/bookings
Authorization: Bearer <token>
```

#### Get My Bookings
```http
GET /api/bookings/my-bookings
Authorization: Bearer <token>
```

#### Get Booking by ID
```http
GET /api/bookings/1
Authorization: Bearer <token>
```

#### Create Booking
```http
POST /api/bookings/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "cartId": 1,
  "specialInstructions": "Please arrive on time",
  "preferredDate": "2024-01-15",
  "preferredTime": "10:00"
}
```

#### Get Bookings by Date
```http
GET /api/bookings/by-date?date=2024-01-15
Authorization: Bearer <token>
```

#### Cancel Booking
```http
DELETE /api/bookings/1
Authorization: Bearer <token>
```

### 7. Payment Management

#### Get All Payments (Admin)
```http
GET /api/payments
Authorization: Bearer <token>
```

#### Get Payment by ID
```http
GET /api/payments/1
Authorization: Bearer <token>
```

#### Create Payment
```http
POST /api/payments/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "bookingId": 1,
  "paymentMode": "CREDIT_CARD",
  "amount": 500.00,
  "cardNumber": "1234567890123456",
  "cardHolderName": "John Doe",
  "expiryDate": "12/25",
  "cvv": "123"
}
```

#### Process Payment
```http
POST /api/payments/1/process
Authorization: Bearer <token>
```

#### Get Payments by Status
```http
GET /api/payments/by-status?status=COMPLETED
Authorization: Bearer <token>
```

#### Get Payments by Amount
```http
GET /api/payments/by-amount?minAmount=100.00
Authorization: Bearer <token>
```

#### Cancel Payment
```http
DELETE /api/payments/1
Authorization: Bearer <token>
```

### 8. Feedback Management

#### Get All Feedback (Admin)
```http
GET /api/feedback
Authorization: Bearer <token>
```

#### Get Feedback by ID
```http
GET /api/feedback/1
Authorization: Bearer <token>
```

#### Create Feedback
```http
POST /api/feedback/create
Authorization: Bearer <token>
Content-Type: application/json

{
  "serviceProviderId": 1,
  "rating": 5,
  "comment": "Excellent service! Very professional and timely."
}
```

#### Get Feedback for Service Provider
```http
GET /api/feedback/service-provider/1
Authorization: Bearer <token>
```

#### Get Feedback by Rating
```http
GET /api/feedback/by-rating?minRating=4
Authorization: Bearer <token>
```

#### Delete Feedback
```http
DELETE /api/feedback/1
Authorization: Bearer <token>
```

### 9. Cart Management

#### Get Cart
```http
GET /api/cart
Authorization: Bearer <token>
```

#### Get Cart Items
```http
GET /api/cart/items
Authorization: Bearer <token>
```

#### Add Item to Cart
```http
POST /api/cart/add?serviceProviderId=1&quantity=1
Authorization: Bearer <token>
```

#### Update Cart Item Quantity
```http
PUT /api/cart/update?cartItemId=1&quantity=2
Authorization: Bearer <token>
```

#### Remove Item from Cart
```http
DELETE /api/cart/remove/1
Authorization: Bearer <token>
```

#### Clear Cart
```http
DELETE /api/cart/clear
Authorization: Bearer <token>
```

## Error Responses

### 400 Bad Request
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/auth/signup"
}
```

### 401 Unauthorized
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 401,
  "error": "Unauthorized",
  "message": "Invalid token",
  "path": "/api/customer/profile"
}
```

### 403 Forbidden
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access denied",
  "path": "/api/admin/customers"
}
```

### 404 Not Found
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Resource not found",
  "path": "/api/services/999"
}
```

### 500 Internal Server Error
```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "path": "/api/bookings/create"
}
```

## Sample Workflow

### 1. Customer Registration and Service Booking

1. **Register as Customer**
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

2. **Sign In**
```bash
curl -X POST "http://localhost:8080/api/auth/signin" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'
```

3. **Add Service to Cart**
```bash
curl -X POST "http://localhost:8080/api/cart/add?serviceProviderId=1&quantity=1" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

4. **Create Booking**
```bash
curl -X POST "http://localhost:8080/api/bookings/create" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "cartId": 1,
    "specialInstructions": "Please arrive on time"
  }'
```

5. **Make Payment**
```bash
curl -X POST "http://localhost:8080/api/payments/create" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "bookingId": 1,
    "paymentMode": "CREDIT_CARD",
    "amount": 500.00
  }'
```

6. **Provide Feedback**
```bash
curl -X POST "http://localhost:8080/api/feedback/create" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "serviceProviderId": 1,
    "rating": 5,
    "comment": "Excellent service!"
  }'
```

## Notes

- All timestamps are in ISO 8601 format
- All monetary values are in the application's base currency
- JWT tokens expire after 24 hours by default
- Passwords are encrypted using BCrypt
- All endpoints return JSON responses
- Pagination is not implemented in this version
- File uploads are not supported in this version 