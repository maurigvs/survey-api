package br.com.maurigvs.surveyapi.service.impl;

import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import br.com.maurigvs.surveyapi.service.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository repository;

    public QuestionServiceImpl(QuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Question question) {
        repository.save(question);
    }

    @Override
    public Question findById(Long questionId) {
        return repository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(questionId));
    }

    @Override
    public void deleteById(Long questionId, Long surveyId) {
        var question = findById(questionId);
        verifyBeforeDelete(question, surveyId);
        repository.delete(question);
    }

    private void verifyBeforeDelete(Question question, Long surveyId) {
        if(!question.getSurvey().getId().equals(surveyId))
            throw new SurveyNotFoundException(surveyId);
    }
}
