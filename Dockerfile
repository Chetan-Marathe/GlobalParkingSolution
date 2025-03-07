# Use an official Maven image with Java
FROM maven:3.9.6-eclipse-temurin-17

# Set working directory inside the container
WORKDIR /app

# Copy Maven project files
COPY . .

# Build the application
RUN mvn clean install

# Expose the application's port (Render will set the actual port)
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "target/parkingsol-0.0.1-SNAPSHOT.jar", "--server.port=8080"]
