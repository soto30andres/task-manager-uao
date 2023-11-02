FROM ubuntu:latest AS build
WORKDIR "/TaskManager"
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
run ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR "/TaskManager"
EXPOSE 8080
COPY /build/libs/TaskManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
