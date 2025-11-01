# Build stage
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copy parent pom and api-gateway pom
COPY pom.xml ./
COPY api-gateway/pom.xml ./api-gateway/

# Download dependencies
RUN mvn -f api-gateway/pom.xml dependency:go-offline

# Copy source code
COPY api-gateway/src ./api-gateway/src

# Build the application
RUN mvn -f api-gateway/pom.xml clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from build stage
COPY --from=build /app/api-gateway/target/*.jar app.jar

# Expose the gateway port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --retries=3 --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
