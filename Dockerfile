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


FROM redis:latest as redis

FROM openjdk:17-alpine

ARG JAR_FILE=/build/libs/sinsungo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /sinsungo.jar

# Redis 이미지에서 Redis 바이너리 및 설정을 가져옴
COPY --from=redis /usr/local/bin/redis* /usr/local/bin/
COPY --from=redis /etc/redis /etc/redis
COPY --from=redis /var/lib/redis /var/lib/redis

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/sinsungo.jar"]
