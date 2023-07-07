FROM maven:3.6.3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests
RUN mkdir images
FROM openjdk:17-alpine
COPY --from=build target/API-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]