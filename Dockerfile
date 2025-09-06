FROM maven:3.8.6-eclipse-temurin-17-alpine

# Set working directory
WORKDIR /app

# Copy root pom.xml first
COPY pom.xml .

# Copy all module pom.xml files
COPY user-service/pom.xml ./user-service/
COPY product-service/pom.xml ./product-service/
COPY order-service/pom.xml ./order-service/
COPY inventory-service/pom.xml ./inventory-service/
COPY payment-service/pom.xml ./payment-service/
COPY notification-service/pom.xml ./notification-service/
COPY api-gateway/pom.xml ./api-gateway/

# Download dependencies for all modules
RUN mvn dependency:go-offline -B

# Copy all source code
COPY user-service/src ./user-service/src
COPY product-service/src ./product-service/src
COPY order-service/src ./order-service/src
COPY inventory-service/src ./inventory-service/src
COPY payment-service/src ./payment-service/src
COPY notification-service/src ./notification-service/src
COPY api-gateway/src ./api-gateway/src

# Build all modules
RUN mvn clean package -DskipTests

# Use a smaller runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR from api-gateway (main entry point)
COPY --from=0 /app/api-gateway/target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
