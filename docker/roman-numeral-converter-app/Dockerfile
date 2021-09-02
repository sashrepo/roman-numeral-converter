FROM openjdk:11-jdk

LABEL maintainer="Saravanesh Selvaraj"
LABEL description="Java 11 app that exposes a rest api to convert Integer to Roman Numeral"

EXPOSE 8080

RUN mkdir -p /app
WORKDIR /app

ARG JAR_FILE=target/*.jar

RUN wget -O apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.2.0/elastic-apm-agent-1.2.0.jar

COPY ${JAR_FILE} /app/app.jar
COPY ../docker/roman-numeral-converter-app/startup.sh /app/startup.sh

RUN chmod +x /app/startup.sh

ENTRYPOINT [ "/bin/bash", "/app/startup.sh" ]