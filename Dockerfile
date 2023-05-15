FROM openjdk:8
ARG JAR_FILE=target/demoapi-0.0.1-SNAPSHOT.jar

EXPOSE 8080
WORKDIR /opt/app
COPY ${JAR_FILE} demoapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demoapi-0.0.1-SNAPSHOT.jar"]