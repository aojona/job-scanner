FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} scheduler-service.jar
ENTRYPOINT ["java", "-jar", "/app/scheduler-service.jar"]