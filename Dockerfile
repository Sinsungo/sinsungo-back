FROM openjdk:17-alpine

WORKDIR /app

COPY ./application.properties ./src/main/resources/application.properties
COPY app.jar .

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-jar", "app.jar"]