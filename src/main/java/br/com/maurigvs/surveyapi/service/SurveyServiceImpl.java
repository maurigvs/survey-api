package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public void createSurvey(Survey survey) {
        verifyBeforeSave(survey);
        surveyRepository.save(survey);
    }

    @Override
    public List<Survey> listAllSurveys() {
        return surveyRepository.findAll();
    }

    private void verifyBeforeSave(Survey survey) {
        if(surveyRepository.existsByTitle(survey.getTitle()))
            throw new SurveyAlreadyExistsException(survey.getTitle());
    }
}