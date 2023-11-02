FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .
RUN cd TaskManager
run ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
RUN cd TaskManager
COPY --from=build /build/libs/TaskManager-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
