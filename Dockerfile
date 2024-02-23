FROM eclipse-temurin:11

COPY build/libs/survey-api-2.0.0-SNAPSHOT.jar survey-api.jar

CMD ["java", "-jar", "survey-api.jar"]