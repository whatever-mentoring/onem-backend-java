# 1. JDK 빌드 이미지 (멀티 스테이지 빌드)
FROM amazoncorretto:17-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# 2. 런타임 이미지 (최적화된 실행 환경)
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# 3. JVM 최적화 설정
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:MinRAMPercentage=75.0"

# 4. 애플리케이션 실행
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
