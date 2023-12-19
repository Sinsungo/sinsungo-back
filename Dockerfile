FROM adoptopenjdk:17-jdk-hotspot AS builder

WORKDIR /app
COPY . .

# Gradle 빌드
RUN ./gradlew bootJar

FROM adoptopenjdk:17-jre-hotspot

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# application.properties 파일 복사
COPY --from=builder /app/src/main/resources/application.properties /app/src/main/resources/application.properties

ENTRYPOINT ["java", "-jar", "app.jar"]
