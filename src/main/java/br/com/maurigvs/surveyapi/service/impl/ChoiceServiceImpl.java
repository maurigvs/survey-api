package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.ChoiceNotFoundException;
import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Choice;
import br.com.maurigvs.surveyapi.repository.ChoiceRepository;
import br.com.maurigvs.surveyapi.service.ChoiceService;
import org.springframework.stereotype.Service;

@Service
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository repository;

    public ChoiceServiceImpl(ChoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Choice choice) {
        repository.save(choice);
    }

    private Choice findById(Long choiceId){
        return repository.findById(choiceId)
                .orElseThrow(() -> new ChoiceNotFoundException(choiceId));
    }

    @Override
    public void deleteById(Long choiceId, Long questionId, Long surveyId) {
        var choice = findById(choiceId);
        verifyBeforeDelete(choice, questionId, surveyId);
        repository.delete(choice);
    }

    private void verifyBeforeDelete(Choice choice, Long questionId, Long surveyId) {
        if(!choice.getQuestion().getId().equals(questionId))
            throw new QuestionNotFoundException(questionId);

        if(!choice.getQuestion().getSurvey().getId().equals(surveyId))
            throw new SurveyNotFoundException(surveyId);
    }
}
