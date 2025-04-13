# Use a base image with JDK 17
FROM openjdk:17-jdk
LABEL authors="Randy"

# Set the working directory
WORKDIR /app

# Copy the application JAR into the container
COPY target/westar-api.jar /app/westar-api.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/westar-api.jar"]