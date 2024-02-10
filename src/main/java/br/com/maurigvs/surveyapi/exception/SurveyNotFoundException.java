package br.com.maurigvs.surveyapi.exception;

public class SurveyNotFoundException extends BadRequestException {

    public SurveyNotFoundException(Integer surveyId) {
        super("Survey not found by Id " + surveyId);
    }
}
