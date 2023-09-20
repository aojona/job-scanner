FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /app
ARG JAR_FILE=./build/libs/*.jar
COPY ${JAR_FILE} job-server.jar
ENTRYPOINT ["java", "-jar", "/app/job-server.jar"]