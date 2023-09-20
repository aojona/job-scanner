FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} rest-api.jar
ENTRYPOINT ["java", "-jar", "/app/rest-api.jar"]