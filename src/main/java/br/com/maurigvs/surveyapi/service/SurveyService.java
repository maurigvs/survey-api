package br.com.maurigvs.surveyapi.service;

import org.springframework.stereotype.Service;

import br.com.maurigvs.surveyapi.model.dto.SurveyDto;
import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public Survey createSurvey(SurveyDto dto) {
        Survey survey = new Survey(dto);
        return surveyRepository.save(survey);
    }
}
