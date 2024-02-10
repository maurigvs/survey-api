package br.com.maurigvs.surveyapi.exception;

public class SurveyAlreadyExistsException extends BadRequestException {

    public SurveyAlreadyExistsException(String surveyTitle) {
        super("Survey '" + surveyTitle + "' already exists");
    }
}
