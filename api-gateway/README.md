# API Gateway - E-commerce Microservices

## Overview

The API Gateway serves as the single entry point for all client requests to the microservices. It provides:

- **Unified API Access** - One endpoint for all services
- **Consolidated Swagger Documentation** - All service APIs in one place
- **Request Routing** - Intelligent routing to appropriate microservices
- **CORS Handling** - Centralized cross-origin configuration

## Architecture

```
Client → Nginx (Port 80) → API Gateway (Port 8080) → Microservices
                                                      ├── User Service (8081)
                                                      ├── Notification Service (8082)
                                                      ├── Product Service (8083)
                                                      ├── Order Service (8084)
                                                      └── Payment Service (8085)
```

## Access Points

### Unified Swagger UI (All Services in ONE place!)
```
http://localhost:8080/swagger-ui.html
or
http://YOUR_EC2_IP:8080/swagger-ui.html
```

### Through Nginx (Port 80)
```
http://localhost/swagger-ui.html
```

### API Endpoints

All API requests go through `/api/` prefix:

```bash
# User Service
POST http://localhost:8080/api/users/register
GET  http://localhost:8080/api/users/profile

# Authentication
POST http://localhost:8080/api/auth/login
POST http://localhost:8080/api/auth/register

# Notifications
POST http://localhost:8080/api/notifications/send
GET  http://localhost:8080/api/notifications/all

# Products
GET  http://localhost:8080/api/products/all
POST http://localhost:8080/api/products/create

# Orders
GET  http://localhost:8080/api/orders/all
POST http://localhost:8080/api/orders/create

# Payments
POST http://localhost:8080/api/payments/process
GET  http://localhost:8080/api/payments/status
```

## Running the Gateway

### Local Development
```bash
cd api-gateway
mvn spring-boot:run
```

### With Docker Compose
```bash
# Start all services including gateway
docker-compose up -d

# Check gateway health
curl http://localhost:8080/health

# View gateway logs
docker logs ecom-api-gateway -f
```

## Configuration

### Routes Configuration
Routes are defined in `application.yml`:
- Each microservice has its own route pattern
- StripPrefix removes `/api/{service}` prefix before forwarding
- Example: `/api/users/profile` → routes to `user-service:8081/user/profile`

### CORS Configuration
Global CORS is enabled for all origins in development. Update in production:
```yaml
spring.cloud.gateway.globalcors.corsConfigurations
```

## Features

### 1. Unified Swagger Documentation
- Access all microservices' API docs from one interface
- Select different services from dropdown in Swagger UI
- No need to visit multiple ports

### 2. Health Checks
```bash
curl http://localhost:8080/actuator/health
```

### 3. API Documentation per Service
```bash
# Get User Service OpenAPI spec
curl http://localhost:8080/v3/api-docs/user-service

# Get Product Service OpenAPI spec
curl http://localhost:8080/v3/api-docs/product-service
```

## Benefits

✅ **Single Entry Point** - Clients only need to know one URL
✅ **Unified Documentation** - All APIs in one Swagger UI
✅ **Simplified Client Code** - No need to track multiple service URLs
✅ **Centralized Security** - Add authentication/authorization at gateway level
✅ **Load Balancing** - Can route to multiple instances of same service
✅ **Monitoring** - Single point to monitor all API traffic

## Troubleshooting

### Gateway Not Starting
```bash
# Check logs
docker logs ecom-api-gateway

# Check if port 8080 is available
netstat -an | grep 8080
```

### Service Not Responding
```bash
# Verify all services are running
docker ps

# Test individual service health
curl http://localhost:8081/actuator/health  # User service
curl http://localhost:8082/actuator/health  # Notification service
```

### Swagger Not Loading
1. Ensure all microservices are running
2. Check that services have `/v3/api-docs` endpoint
3. Verify network connectivity between gateway and services

## Next Steps

- Add JWT authentication at gateway level
- Implement rate limiting
- Add request/response logging
- Set up distributed tracing
- Configure circuit breakers
