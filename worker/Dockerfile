FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /build
COPY pom.xml .
COPY common ./common
COPY api ./api
COPY worker ./worker
RUN mvn -pl worker -am package -DskipTests

FROM eclipse-temurin:25-jre
COPY --from=build /build/worker/target/worker-*.jar worker.jar
ENTRYPOINT ["java","-jar","/worker.jar"]