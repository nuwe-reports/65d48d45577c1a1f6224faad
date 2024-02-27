
FROM maven:3.8.4 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn test

FROM maven:3.8.4 AS package
WORKDIR /app
COPY --from=build /app/target .

FROM openjdk:11
WORKDIR /app
COPY --from=package /app/maven-wrapper.jar .
CMD ["java", "-jar", "maven-wrapper.jar"]
