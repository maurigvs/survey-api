package br.com.maurigvs.surveyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maurigvs.surveyapi.controller.SurveyController.SurveyRequest;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@Service
public class SurveyService {

    @Autowired
    SurveyRepository repository;

    public void createSurvey(SurveyRequest request) {
        Survey survey = parseRequest(request);
        repository.save(survey);
    }

    private Survey parseRequest(SurveyRequest request) {
        Survey survey = new Survey();
        survey.setTitle(request.getTitle());
        return survey;
    }
}