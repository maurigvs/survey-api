FROM adoptopenjdk/openjdk11:alpine

COPY build/libs/survey-api-0.0.1-SNAPSHOT.jar survey-api.jar

ENTRYPOINT ["java","-jar","/survey-api.jar"]
