package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Survey;

import java.util.List;

public interface SurveyService {

    void create(Survey survey);

    Survey findById(Long surveyId);

    List<Survey> findAll();

    void deleteById(Long surveyId);

}
