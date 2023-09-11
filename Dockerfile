FROM openjdk:17-jdk-slim

ARG APP_NAME
ARG APP_VERSION

EXPOSE 8080

# Copy the JAR file to the image
COPY ./build/libs/${APP_NAME}-${APP_VERSION}.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
