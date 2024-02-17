package br.com.maurigvs.surveyapi.exception;

public class SurveyNotFoundException extends BadRequestException {

    public SurveyNotFoundException(Long surveyId) {
        super("Survey not found by Id " + surveyId);
    }
}
