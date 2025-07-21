# Stage 1: Build using JDK 17
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests && cp target/*.jar app.jar

# Stage 2: Run using lightweight JDK 17
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp

COPY --from=build /app/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
