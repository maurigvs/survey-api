FROM eclipse-temurin:11

RUN mkdir /opt/app

COPY build/libs/survey-api-0.0.1-SNAPSHOT.jar /opt/app/survey-api.jar

CMD ["java", "-jar", "/opt/app/survey-api.jar"]