FROM openjdk:21-jdk-slim

COPY build/libs/*.jar app.jar

ARG PROFILE
ENV PROFILE=${PROFILE}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=${PROFILE}"]
