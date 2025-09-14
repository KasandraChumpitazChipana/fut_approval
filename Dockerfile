FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/vg-ms-futApproval-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8107

ENTRYPOINT ["java", "-jar", "app.jar"]
