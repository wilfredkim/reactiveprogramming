# Use a base image with Java pre-installed
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/reactive-programming-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application runs on
EXPOSE 1724

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
