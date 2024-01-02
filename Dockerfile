#FROM openjdk:17-alpine
#
#WORKDIR /app
#
##COPY ./src/main/resources/application.properties ./application.properties
#
#COPY build/libs/*.jar app.jar
#
#ENV TZ=Asia/Seoul
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]


FROM openjdk:17-alpine

ARG JAR_FILE=/build/libs/sinsungo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /sinsungo.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/sinsungo.jar"]