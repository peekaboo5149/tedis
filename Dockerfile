# Use a Maven image as a builder
FROM maven:3.8.4-openjdk-17-slim as builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src .

RUN mvn clean package
RUN mv target/tedis*.jar target/app.jar

# Final image with only JRE
FROM openjdk:17-jdk-slim AS final

WORKDIR /app

COPY --from=builder /app/target/app.jar ./app.jar

CMD ["java", "-jar", "app.jar"]



