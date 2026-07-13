# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src
# Build the application (skip tests in Docker to speed up image creation, tests run in CI pipeline)
RUN mvn clean package -DskipTests

# Stage 2: Create the lightweight runtime container
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the built jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose default application port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
