FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} telegram-service.jar
ENTRYPOINT ["java", "-jar", "/app/telegram-service.jar"]