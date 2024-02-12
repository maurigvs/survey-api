package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.SurveyAlreadyExistsException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository repository;

    public SurveyServiceImpl(SurveyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Survey survey) {
        verifyBeforeSave(survey);
        repository.save(survey);
    }

    @Override
    public Survey findById(Long surveyId) {
        return repository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));
    }

    @Override
    public List<Survey> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long surveyId) {
        var survey = findById(surveyId);
        repository.delete(survey);
    }

    private void verifyBeforeSave(Survey survey) {
        if(repository.existsByTitle(survey.getTitle()))
            throw new SurveyAlreadyExistsException(survey.getTitle());
    }
}
