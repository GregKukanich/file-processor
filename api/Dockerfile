FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /build
COPY pom.xml .
COPY common ./common
COPY api ./api
COPY worker ./worker
RUN mvn -pl api -am package -DskipTests

FROM eclipse-temurin:25-jre
COPY --from=build /build/api/target/api-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]