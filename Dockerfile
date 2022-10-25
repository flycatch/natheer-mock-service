FROM maven:3-jdk-11 AS builder
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn -B dependency:go-offline
COPY src /build/src
RUN mvn package -DskipTests


FROM openjdk:11-slim
RUN mkdir /app
WORKDIR /app
COPY --from=builder /build/target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
