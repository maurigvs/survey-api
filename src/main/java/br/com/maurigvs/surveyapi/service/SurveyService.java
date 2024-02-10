package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.entity.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public void createSurvey(Survey survey){
        surveyRepository.save(survey);
    }

    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }
}
