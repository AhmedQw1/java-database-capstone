# Stage 1: The Build Stage
# This stage uses a full JDK and Maven to compile the Java application and build the JAR file.
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the pom.xml and download dependencies first to leverage Docker's layer caching
COPY backend/pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the backend source code
COPY backend/src ./src

# Package the application, skipping tests to speed up the build process
RUN mvn package -DskipTests

# ---

# Stage 2: The Runtime Stage
# This stage uses a lightweight JRE (Java Runtime Environment) image, which is smaller and more secure.
FROM eclipse-temurin:21-jre-focal
WORKDIR /app

# Copy only the compiled JAR file from the 'build' stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the Spring Boot application runs on
EXPOSE 8080

# Define the command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]