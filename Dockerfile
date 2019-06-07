FROM openjdk:8-jre-alpine
LABEL maintainer="SET YOUR OWN MAINTAINER"

RUN mkdir -p /opt/openex
WORKDIR /opt/openex
COPY target/com.openex.docker.demo-*.jar openex.jar
COPY src/main/resources/application.properties application.properties

ENTRYPOINT ["java", "-jar", "openex.jar"]
EXPOSE 8080
