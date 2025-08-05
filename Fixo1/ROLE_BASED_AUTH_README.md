# Role-Based Authentication System

This Spring Boot application implements a comprehensive role-based authentication system with three user roles: **CUSTOMER**, **SERVICEPROVIDER**, and **ADMIN**.

## User Roles

### 1. CUSTOMER
- Can book services
- Can view available services
- Can submit feedback
- Can manage their profile and bookings

### 2. SERVICEPROVIDER
- Can add, update, and remove services
- Can view their bookings
- Can manage their business profile
- Can view service categories

### 3. ADMIN
- Can manage all users
- Can approve service providers
- Can view system reports
- Can access admin dashboard
- Has access to all system features

## Authentication Endpoints

### Sign Up Endpoints

#### 1. General Sign Up
```
POST /api/auth/signup
```
- Allows registration with any role
- Request body: `SignUpRequest`

#### 2. Customer Sign Up
```
POST /api/auth/signup/customer
```
- Specifically for customer registration
- Request body: `CustomerSignUpRequest`
- Automatically sets role to CUSTOMER

#### 3. Service Provider Sign Up
```
POST /api/auth/signup/service-provider
```
- Specifically for service provider registration
- Request body: `ServiceProviderSignUpRequest`
- Automatically sets role to SERVICEPROVIDER

### Sign In Endpoints

#### 1. General Sign In
```
POST /api/auth/signin
```
- Allows sign in for any role
- Optional role validation

#### 2. Role-Specific Sign In
```
POST /api/auth/signin/customer
POST /api/auth/signin/service-provider
POST /api/auth/signin/admin
```
- Validates user has the specified role
- Returns error if user doesn't have the required role

## Request/Response DTOs

### SignUpRequest
```json
{
  "email": "user@example.com",
  "password": "password123",
  "phoneNo": "1234567890",
  "userRole": "CUSTOMER"
}
```

### CustomerSignUpRequest
```json
{
  "email": "customer@example.com",
  "password": "password123",
  "phoneNo": "1234567890",
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main St, City, State"
}
```

### ServiceProviderSignUpRequest
```json
{
  "email": "provider@example.com",
  "password": "password123",
  "phoneNo": "1234567890",
  "businessName": "ABC Services",
  "businessAddress": "456 Business Ave, City, State",
  "serviceCategory": "Plumbing",
  "licenseNumber": "LIC123456",
  "businessDescription": "Professional plumbing services"
}
```

### SignInRequest
```json
{
  "email": "user@example.com",
  "password": "password123",
  "userRole": "CUSTOMER"  // Optional
}
```

### AuthResponse
```json
{
  "token": "jwt_token_here",
  "email": "user@example.com",
  "userRole": "CUSTOMER",
  "message": "Login successful",
  "success": true
}
```

## Role-Based API Endpoints

### Customer Endpoints (`/api/customer/**`)
- Requires CUSTOMER role
- Endpoints:
  - `GET /api/customer/profile`
  - `GET /api/customer/bookings`
  - `POST /api/customer/book-service`
  - `GET /api/customer/services`
  - `POST /api/customer/feedback`

### Service Provider Endpoints (`/api/service-provider/**`)
- Requires SERVICEPROVIDER role
- Endpoints:
  - `GET /api/service-provider/profile`
  - `GET /api/service-provider/services`
  - `POST /api/service-provider/add-service`
  - `GET /api/service-provider/bookings`
  - `PUT /api/service-provider/update-service`
  - `DELETE /api/service-provider/remove-service`

### Admin Endpoints (`/api/admin/**`)
- Requires ADMIN role
- Endpoints:
  - `GET /api/admin/dashboard`
  - `GET /api/admin/users`
  - `GET /api/admin/service-providers`
  - `GET /api/admin/customers`
  - `POST /api/admin/approve-service-provider`
  - `DELETE /api/admin/delete-user`
  - `GET /api/admin/reports`

### Shared Endpoints (`/api/shared/**`)
- Accessible by multiple roles
- Endpoints:
  - `GET /api/shared/services` (CUSTOMER, SERVICEPROVIDER, ADMIN)
  - `GET /api/shared/bookings` (CUSTOMER, SERVICEPROVIDER, ADMIN)
  - `GET /api/shared/profile` (All authenticated users)
  - `PUT /api/shared/profile` (All authenticated users)
  - `GET /api/shared/categories` (CUSTOMER, SERVICEPROVIDER, ADMIN)

## Security Configuration

The application uses Spring Security with JWT authentication and role-based authorization:

### Security Rules
- `/api/auth/**` - Public access (no authentication required)
- `/api/admin/**` - Requires ADMIN role
- `/api/customer/**` - Requires CUSTOMER role
- `/api/service-provider/**` - Requires SERVICEPROVIDER role
- `/api/services/**` - Requires any authenticated role
- `/api/bookings/**` - Requires any authenticated role
- All other endpoints require authentication

### JWT Token
- Contains user email and role information
- Used for authentication in subsequent requests
- Include in Authorization header: `Bearer <token>`

## Usage Examples

### 1. Customer Registration
```bash
curl -X POST http://localhost:8080/api/auth/signup/customer \
  -H "Content-Type: application/json" \
  -d '{
    "email": "customer@example.com",
    "password": "password123",
    "phoneNo": "1234567890",
    "firstName": "John",
    "lastName": "Doe",
    "address": "123 Main St, City, State"
  }'
```

### 2. Service Provider Registration
```bash
curl -X POST http://localhost:8080/api/auth/signup/service-provider \
  -H "Content-Type: application/json" \
  -d '{
    "email": "provider@example.com",
    "password": "password123",
    "phoneNo": "1234567890",
    "businessName": "ABC Services",
    "businessAddress": "456 Business Ave, City, State",
    "serviceCategory": "Plumbing",
    "licenseNumber": "LIC123456",
    "businessDescription": "Professional plumbing services"
  }'
```

### 3. Role-Specific Sign In
```bash
curl -X POST http://localhost:8080/api/auth/signin/customer \
  -H "Content-Type: application/json" \
  -d '{
    "email": "customer@example.com",
    "password": "password123"
  }'
```

### 4. Accessing Role-Specific Endpoints
```bash
# Customer accessing their profile
curl -X GET http://localhost:8080/api/customer/profile \
  -H "Authorization: Bearer <jwt_token>"

# Service provider accessing their services
curl -X GET http://localhost:8080/api/service-provider/services \
  -H "Authorization: Bearer <jwt_token>"

# Admin accessing dashboard
curl -X GET http://localhost:8080/api/admin/dashboard \
  -H "Authorization: Bearer <jwt_token>"
```

## Error Handling

The system provides clear error messages for:
- Invalid credentials
- Role mismatch during sign in
- Unauthorized access to role-specific endpoints
- Duplicate email/phone number during registration
- Invalid request data

## Testing

You can test the role-based authentication using:
1. Swagger UI: `http://localhost:8080/swagger-ui/index.html`
2. Postman or any REST client
3. cURL commands as shown in the examples above

## Database Schema

The system uses the following entities:
- `User` - Base user entity with authentication info
- `Customer` - Customer-specific information
- `ServiceProvider` - Service provider-specific information
- `UserRole` - Enum defining available roles

## Future Enhancements

Potential improvements:
1. Add role-based password policies
2. Implement role-based rate limiting
3. Add audit logging for role-based actions
4. Implement role-based notification systems
5. Add role-based data access controls 