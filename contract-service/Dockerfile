FROM eclipse-temurin:17
WORKDIR /app
COPY target/contract-service-0.0.1-SNAPSHOT.jar /app/contract-service.jar
ENTRYPOINT["java", "-jar", "contract-service.jar"]