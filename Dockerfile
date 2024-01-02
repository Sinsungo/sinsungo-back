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

# Redis 서버 실행
CMD ["redis-server"]

# 어플리케이션 실행
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/sinsungo.jar"]
