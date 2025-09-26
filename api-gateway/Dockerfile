FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -pl api-gateway -am -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=api-gateway/target/api-gateway-*.jar
COPY --from=build /app/${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]