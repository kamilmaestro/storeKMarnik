FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ./target/store-0.0.1-SNAPSHOT.jar store.jar
ENTRYPOINT ["java","-jar","/store.jar"]
