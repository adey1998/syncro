# Use an official OpenJDK image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from your machine into the image
COPY target/syncro-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
