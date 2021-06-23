FROM openjdk:8-jre-alpine
WORKDIR /app
COPY target/titan-0.0.5.jar .
ENTRYPOINT [ "java", "-jar", "titan-0.0.5.jar" ]