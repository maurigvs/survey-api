FROM eclipse-temurin:11

RUN mkdir /opt/app

COPY build/libs/ /build/

COPY /build/survey-api-2.0.0-SNAPSHOT.jar /opt/app/survey-api.jar

CMD ["java", "-jar", "/opt/app/survey-api.jar"]