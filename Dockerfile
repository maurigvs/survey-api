FROM eclipse-temurin:11

COPY build/libs/survey-api-4.0.0-SNAPSHOT.jar survey-api-latest.jar

CMD ["java", "-jar", "/app/survey-api-latest.jar"]