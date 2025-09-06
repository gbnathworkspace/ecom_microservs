FROM maven:3.8.6-eclipse-temurin-17-alpine AS builder

# Set working directory
WORKDIR /app

# Copy parent pom.xml first
COPY ../pom.xml ./parent-pom.xml

# Copy this service's pom.xml
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B -f pom.xml

# Copy source code
COPY src ./src

# Build the service
RUN mvn clean package -DskipTests

# Use smaller runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR
COPY --from=builder /app/target/*.jar notification-service.jar

# Create non-root user for security
RUN addgroup -g 1001 -S appgroup && \
    adduser -S appuser -u 1001 -G appgroup

USER appuser

# Expose the service port
EXPOSE 8082

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8082/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "notification-service.jar"]