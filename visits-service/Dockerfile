FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -pl visits-service -am -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=visits-service/target/visits-service-*.jar
COPY --from=build /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]