package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;

public interface SurveyService {

    /**
     * Create a new survey with questions and choices.
     * @param survey Survey object.
     * @throws SurveyAlreadyExistsException when a survey with the same Title already exists in the database.
     */
    void createSurvey(Survey survey) throws SurveyAlreadyExistsException;


    /**
     * @return List of all Surveys in the database.
     */
    List<Survey> listAllSurveys();


    /**
     * Find a Survey by the Id given.
     * @param surveyId Survey Id.
     * @return Survey found.
     * @throws SurveyNotFoundException when a survey is not found by the Id given.
     */
    Survey findById(Integer surveyId) throws SurveyNotFoundException;
}
