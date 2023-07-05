FROM maven:3.6.3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/API-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]