# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /workspace/app

# Copy only the files needed for building
COPY pom.xml .
COPY src src/

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Create the production image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /workspace/app/target/quarkus-app/lib/ /app/lib/
COPY --from=build /workspace/app/target/quarkus-app/*.jar /app/
COPY --from=build /workspace/app/target/quarkus-app/app/ /app/app/
COPY --from=build /workspace/app/target/quarkus-app/quarkus/ /app/quarkus/

# Set the startup command to run the application
ENV QUARKUS_HTTP_PORT=8080
EXPOSE 8080

# Use shell form to enable environment variable substitution
CMD ["java", "-jar", "quarkus-run.jar"]
