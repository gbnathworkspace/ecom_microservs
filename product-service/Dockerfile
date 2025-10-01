FROM maven:3.8.6-eclipse-temurin-17-alpine AS builder

WORKDIR /build

# Copy the entire project context (this Dockerfile should be built from root)
COPY . .

# Build only this specific service
RUN mvn clean package -DskipTests -pl product-service -am

# Use smaller runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the built JAR
COPY --from=builder /build/product-service/target/*.jar product-service.jar

# Create non-root user for security
RUN addgroup -g 1001 -S appgroup && \
    adduser -S appuser -u 1001 -G appgroup

USER appuser

# Expose the service port
EXPOSE 8083

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8081/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]