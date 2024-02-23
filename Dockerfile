FROM eclipse-temurin:11

COPY . /app
WORKDIR /app

COPY build/libs/survey-api-2.0.0-SNAPSHOT.jar survey-api-latest.jar

CMD ["java", "-jar", "/app/survey-api-latest.jar"]