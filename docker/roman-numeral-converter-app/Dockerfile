FROM openjdk:11-jdk

LABEL maintainer="Saravanesh Selvaraj"
LABEL description="Java 11 app that exposes a rest api to convert Integer to Roman Numeral"

EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]