FROM maven:3.9-eclise-temurin-17 as build

WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclise-temrin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN mkdir -p uploads/images

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]