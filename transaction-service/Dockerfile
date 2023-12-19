FROM eclipse-temurin:17
WORKDIR /app
COPY target/transaction-service-0.0.1-SNAPSHOT.jar /app/transaction-service.jar
ENTRYPOINT["java", "-jar", "transaction-service.jar"]