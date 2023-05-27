clear
docker stop surveyapi
docker build -t surveyapi .
docker run -p8080:8080 --name surveyapi --rm -d surveyapi
