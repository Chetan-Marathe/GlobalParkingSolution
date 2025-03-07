FROM ubuntu:latest
LABEL authors="marat"

ENTRYPOINT ["top", "-b"]
# Use an official Maven image with Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application (creates JAR file in target/)
RUN mvn clean install

# Use a lightweight Java image to run the application
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR file from the Maven image
COPY --from=build /app/target/parkingsol-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port (Render will set the actual port)
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "app.jar", "--server.port=8080"]
