package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;

public interface SurveyService {

    /**
     * Create a new survey with questions and choices.
     * @param survey
     * @throws br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException when a survey with the same Title already exists in the database.
     */
    void createSurvey(Survey survey);

    /**
     * @return list of all surveys in the database.
     */
    List<Survey> listAllSurveys();
}
