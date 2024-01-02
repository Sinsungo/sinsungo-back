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

# Redis 데이터를 /data 디렉토리에 복사
COPY --from=redis /data /var/lib/redis

COPY ${JAR_FILE} /sinsungo.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod", "/sinsungo.jar"]
