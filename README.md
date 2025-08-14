# E-commerce Microservices Platform

A production-ready, scalable e-commerce platform built with Java 17 + Spring Boot 3.x microservices architecture.

## 🏗️ Architecture

- **6 Core Microservices**: User, Product, Order, Inventory, Payment, Notification
- **Event-Driven**: Apache Kafka for async communication
- **API Gateway**: Spring Cloud Gateway for routing & authentication  
- **Polyglot Persistence**: PostgreSQL + Redis + Elasticsearch
- **Containerized**: Docker + Kubernetes deployment

## 🚀 Quick Start

```bash
# Clone repository
git clone <your-repo-url>
cd ecom_microservs

# Run with Docker Compose
docker-compose up -d

# Or run individual service
./mvnw spring-boot:run
```

## 📊 Services

| Service | Port | Description |
|---------|------|-------------|
| User Service | 8081 | Authentication, JWT, User management |
| Product Service | 8082 | Catalog, Search, Categories |
| Order Service | 8083 | Order processing, SAGA pattern |
| Inventory Service | 8084 | Stock management, Reservations |
| Payment Service | 8085 | Payment processing, Transactions |
| Notification Service | 8086 | Email, SMS, Push notifications |

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.x, Spring Security
- **Database**: PostgreSQL (primary), Redis (cache), Elasticsearch (search)
- **Messaging**: Apache Kafka
- **Gateway**: Spring Cloud Gateway
- **Discovery**: Eureka Server
- **Monitoring**: Prometheus + Grafana + Zipkin
- **Deployment**: Docker, Kubernetes

## 📋 Features

✅ **Implemented**
- JWT Authentication & Authorization
- User registration/login with secure password hashing
- Global exception handling
- Database health checks
- Comprehensive API documentation

🔄 **In Progress**
- Product catalog with search
- Order processing with inventory checks
- Payment integration
- Real-time notifications

📅 **Planned**
- Event-driven architecture with Kafka
- SAGA pattern for distributed transactions
- CQRS implementation
- Kubernetes deployment
- Performance monitoring

## 🔗 API Endpoints

### User Service (8081)
```
POST /api/auth/register - User registration
POST /api/auth/login    - User login
GET  /api/users/profile - Get user profile
GET  /api/health        - Health check
```

## 🏃‍♂️ Development

```bash
# Test the application
./mvnw test

# Run specific service
./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081

# Build for production
./mvnw clean package -DskipTests
```

## 🐳 Docker

```bash
# Build image
docker build -t ecom-user-service .

# Run with compose
docker-compose up -d
```

## 📈 Monitoring

- **Metrics**: http://localhost:9090 (Prometheus)
- **Dashboards**: http://localhost:3000 (Grafana)  
- **Tracing**: http://localhost:9411 (Zipkin)

## 🔒 Security

- JWT-based stateless authentication
- BCrypt password hashing
- Input validation & sanitization
- CORS configuration
- Rate limiting (planned)

## 🧪 Testing

```bash
# Unit tests
./mvnw test

# Integration tests  
./mvnw verify

# Load testing (planned)
./mvnw gatling:test
```

## 📚 Documentation

- [System Architecture](docs/system-architecture.md)
- [Resume Talking Points](docs/resume-talking-points.md)
- [API Documentation](docs/api-docs.md) (coming soon)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note**: This is a learning/portfolio project demonstrating microservices patterns and modern Java development practices.