FROM eclipse-temurin:11

RUN mkdir /opt/app

COPY ./build/ /opt/app/build/

WORKDIR /opt/app

COPY build/libs/survey-api-2.0.0-SNAPSHOT.jar survey-api-latest.jar

CMD ["java", "-jar", "/app/survey-api-latest.jar"]