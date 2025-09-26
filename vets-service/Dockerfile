FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -pl vets-service -am -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=vets-service/target/vets-service-*.jar
COPY --from=build /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]