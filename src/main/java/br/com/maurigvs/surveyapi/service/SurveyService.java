package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;

public interface SurveyService {

    void createSurvey(Survey survey);

    List<Survey> listAllSurveys();
}
