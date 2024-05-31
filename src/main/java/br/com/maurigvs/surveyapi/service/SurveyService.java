package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Survey;
import br.com.maurigvs.surveyapi.repository.SurveyRepository;
import org.springframework.stereotype.Service;

@Service
public class SurveyService extends AbstractCrudService<Survey, SurveyRepository> {

    public SurveyService(SurveyRepository repository) {
        super(repository, Survey.class);
    }
}
