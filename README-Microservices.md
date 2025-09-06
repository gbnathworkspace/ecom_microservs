# Ecom Microservices Architecture

## 🏗️ Architecture Overview

This project uses a true microservices architecture with multiple independent services:

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────────┐
│   Nginx         │    │  User Service    │    │ Notification Service │
│ Load Balancer   │────│    Port: 8081    │    │     Port: 8082      │
│   Port: 80      │    │                  │    │                     │
└─────────────────┘    └──────────────────┘    └─────────────────────┘
         │                        │                        │
         └────────────────────────┼────────────────────────┘
                                  │
                      ┌──────────────────┐
                      │   PostgreSQL     │
                      │   Port: 5432     │
                      └──────────────────┘
```

## 🚀 Services

### Currently Implemented:
- **User Service** (8081): Authentication, user management
- **Notification Service** (8082): Email/SMS notifications
- **Load Balancer** (80): Nginx reverse proxy

### Planned Services:
- **Product Service** (8083): Product catalog
- **Order Service** (8084): Order processing
- **Payment Service** (8085): Payment handling
- **Inventory Service** (8086): Stock management
- **API Gateway** (8080): Centralized API management

## 🛠️ Development Setup

### Prerequisites:
- Docker & Docker Compose
- Java 17
- Maven 3.8+

### Local Development:

1. **Clone and Build:**
   ```bash
   git clone <repository>
   cd ecom_microservs
   mvn clean install
   ```

2. **Start All Services:**
   ```bash
   docker-compose up -d
   ```

3. **Access Services:**
   - **Load Balancer**: http://localhost:80
   - **User Service**: http://localhost:8081
   - **Notification Service**: http://localhost:8082
   - **Database**: localhost:5432

4. **View Logs:**
   ```bash
   docker-compose logs -f [service-name]
   ```

5. **Stop Services:**
   ```bash
   docker-compose down
   ```

### Individual Service Development:

Build and run a single service:
```bash
cd user-service
mvn clean package
docker build -t user-service .
docker run -p 8081:8081 user-service
```

## 📊 Health Checks

Each service provides health endpoints:
- User Service: `GET /actuator/health`
- Notification Service: `GET /actuator/health`
- Load Balancer: `GET /health`

## 🔄 CI/CD Pipeline

The pipeline now builds each service independently:

1. **Test**: Runs tests for all modules
2. **Build**: Creates Docker images for each service
3. **Deploy**: Deploys using Docker Compose

### Pipeline Matrix Strategy:
```yaml
strategy:
  matrix:
    service: [user-service, notification-service]
```

## 🌐 API Routes

### Via Load Balancer (Port 80):
- `GET /api/users/*` → User Service
- `GET /api/notifications/*` → Notification Service
- `GET /health` → Load Balancer Health

### Direct Service Access:
- `http://localhost:8081/*` → User Service
- `http://localhost:8082/*` → Notification Service

## 📁 Project Structure

```
ecom_microservs/
├── user-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── notification-service/
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── docker-compose.yml
├── nginx/
│   └── nginx.conf
├── pom.xml (parent)
└── .github/workflows/
    └── ci-cd.yml
```

## 🔧 Configuration

### Environment Variables:
- `SPRING_DATASOURCE_URL`: Database connection
- `SPRING_DATASOURCE_USERNAME`: DB username
- `SPRING_DATASOURCE_PASSWORD`: DB password
- `SPRING_PROFILES_ACTIVE`: Active Spring profile

### Database:
- **Development**: H2 (in-memory)
- **Production**: PostgreSQL (containerized)

## 🚦 Service Communication

Services communicate via:
1. **HTTP REST APIs**: For synchronous communication
2. **Database**: Shared PostgreSQL instance
3. **Load Balancer**: Nginx for request routing

## 📈 Scaling

To scale services:
```bash
docker-compose up -d --scale user-service=3
docker-compose up -d --scale notification-service=2
```

## 🔍 Monitoring

Check service status:
```bash
docker-compose ps
curl http://localhost:80/health
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
```