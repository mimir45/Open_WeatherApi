FROM openjdk:23-jdk-slim AS build
LABEL authors="mimir"
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
RUN ./mvnw package

FROM openjdk:23-jdk-slim
WORKDIR weatherApi
COPY --from=build target/*.jar weatherApi.jar
ENTRYPOINT ["java","-jar","weatherApi.jar"]