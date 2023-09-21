FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} web-ui.jar
ENTRYPOINT ["java", "-jar", "/app/web-ui.jar"]
EXPOSE 8081