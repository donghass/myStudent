# 1단계: 빌드용 이미지
FROM gradle:7.6-jdk17-alpine AS builder
WORKDIR /workspace
COPY . .
RUN gradle build -x test --no-daemon

# 2단계: 실행용 이미지
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
