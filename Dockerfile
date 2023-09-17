FROM gradle:jdk17 AS build

COPY . /app
WORKDIR /app
RUN gradle build

FROM openjdk:17-jdk-slim

ARG APP_NAME
ARG APP_VERSION

WORKDIR /app
COPY --from=build /app/build/libs/${APP_NAME}-${APP_VERSION}.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]



