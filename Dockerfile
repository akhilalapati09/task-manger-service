FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build application
RUN ./mvnw clean package -DskipTests

# Run application
EXPOSE 8080
CMD ["java", "-jar", "target/quarkus-app/quarkus-run.jar"]
