FROM maven:3.8.1-openjdk-17 AS build

WORKDIR /app

COPY pom.xml /app

COPY . /app

RUN mvn clean install

FROM openjdk:17

WORKDIR /app

COPY --from=build /app/target/deal-0.0.1-SNAPSHOT.jar .
# Set the command to run the application
CMD ["java", "-jar", "deal-0.0.1-SNAPSHOT.jar"]