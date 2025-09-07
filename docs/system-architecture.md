# E-commerce System Architecture

## Overview
This is a microservices-based e-commerce platform. Each service handles specific business logic and communicates through REST APIs and Kafka events.

## High Level Design

```
Internet
    |
    v
[Load Balancer]
    |
    v
[API Gateway] ---- [Service Discovery]
    |
    +-- User Service (8081)
    |-- Product Service (8082)  
    |-- Order Service (8083)
    |-- Inventory Service (8084)
    |-- Payment Service (8085)
    |-- Notification Service (8086)
    |
    v
[Kafka Message Bus]
    |
    v
[PostgreSQL] + [Redis] + [Elasticsearch]
```

## Services

**User Service** (Port 8081)
- User registration and login
- JWT token generation
- Profile management
- Uses: PostgreSQL + Redis for sessions

**Product Service** (Port 8083)  
- Product catalog CRUD
- Category management
- Search functionality
- Uses: PostgreSQL + Elasticsearch for search

**Order Service** (Port 8083)
- Order creation and management
- Order status tracking
- SAGA pattern for distributed transactions
- Uses: PostgreSQL

**Inventory Service** (Port 8084)
- Stock level management
- Product availability checks
- Inventory reservations
- Uses: PostgreSQL

**Payment Service** (Port 8085)
- Payment processing
- Integration with payment gateways
- Transaction records
- Uses: PostgreSQL

**Notification Service** (Port 8082)
- Email notifications
- SMS alerts  
- Push notifications
- Uses: PostgreSQL for templates

## Data Flow

### Order Creation Flow:
1. User creates order via API Gateway
2. Order Service validates and saves order
3. Order Service publishes "order_created" event to Kafka
4. Inventory Service consumes event, reserves stock
5. Payment Service processes payment
6. Notification Service sends confirmation email
7. Order status updated based on payment result

## Technology Stack

**Backend:** Java 17 + Spring Boot 3.x
**Database:** PostgreSQL (primary), Redis (cache), Elasticsearch (search)
**Messaging:** Apache Kafka
**API Gateway:** Spring Cloud Gateway
**Service Discovery:** Eureka
**Containerization:** Docker + Kubernetes
**Monitoring:** Prometheus + Grafana

## Database Schema (PostgreSQL)

Each service has its own database schema:

```sql
-- User Service
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255),
    created_at TIMESTAMP
);

-- Product Service  
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    description TEXT,
    price DECIMAL(10,2),
    category_id INTEGER,
    created_at TIMESTAMP
);

-- Order Service
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    status VARCHAR(50),
    total_amount DECIMAL(10,2),
    created_at TIMESTAMP
);

-- Similar tables for other services...
```

## API Endpoints

**User Service:**
- POST /api/users/register
- POST /api/users/login  
- GET /api/users/profile
- PUT /api/users/profile

**Product Service:**
- GET /api/products
- GET /api/products/{id}
- POST /api/products/search
- GET /api/categories

**Order Service:**
- POST /api/orders
- GET /api/orders/{id}
- GET /api/users/{userId}/orders
- PUT /api/orders/{id}/status

## Event Schema (Kafka Topics)

**order_created:**
```json
{
  "orderId": "12345",
  "userId": "67890", 
  "items": [
    {"productId": "1", "quantity": 2, "price": 29.99}
  ],
  "totalAmount": 59.98,
  "timestamp": "2024-01-15T10:30:00Z"
}
```

**payment_processed:**
```json
{
  "paymentId": "pay_123",
  "orderId": "12345",
  "amount": 59.98,
  "status": "SUCCESS",
  "timestamp": "2024-01-15T10:31:00Z"
}
```

## Deployment

**Development:** Docker Compose
```bash
docker-compose up -d
```

**Production:** Kubernetes
- Each service deployed as separate pods
- Auto-scaling based on CPU/memory usage
- Rolling updates for zero-downtime deployments

## Monitoring

- **Metrics:** Prometheus collects metrics from all services
- **Dashboards:** Grafana for visualization
- **Tracing:** Zipkin for request tracing across services
- **Logs:** ELK stack for centralized logging

## Configuration

**application.yml example:**
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/userdb
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  
  kafka:
    bootstrap-servers: localhost:9092
    
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```

This architecture follows microservices best practices while keeping things simple and maintainable.
