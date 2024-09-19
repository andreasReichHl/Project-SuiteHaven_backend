FROM maven:3.9.9-eclipse-temurin-22 AS builder
WORKDIR /app
COPY ./pom.xml .
COPY ./src ./src
RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin:22
WORKDIR /app
COPY --from=builder ./app/target/*.jar ./app.jar
CMD ["java", "-jar", "app.jar"]