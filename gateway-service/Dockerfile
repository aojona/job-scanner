FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} gateway-service.jar
ENTRYPOINT ["java", "-jar", "/app/gateway-service.jar"]