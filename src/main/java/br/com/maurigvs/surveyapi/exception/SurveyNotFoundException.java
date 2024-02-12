package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.Survey;

public class SurveyNotFoundException extends EntityNotFoundException {

    public SurveyNotFoundException(Integer surveyId) {
        super(Survey.class.getSimpleName(), surveyId);
    }
}
