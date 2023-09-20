FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} rate-limiter-service.jar
ENTRYPOINT ["java", "-jar", "/app/rate-limiter-service.jar"]