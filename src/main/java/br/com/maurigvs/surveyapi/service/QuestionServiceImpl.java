package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.QuestionNotFoundException;
import br.com.maurigvs.surveyapi.exception.SurveyNotFoundException;
import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void deleteById(Long questionId, Long surveyId) {
        var question = findById(questionId);
        verifyBeforeDelete(question, surveyId);
        questionRepository.delete(question);
    }

    private Question findById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(id));
    }

    private void verifyBeforeDelete(Question question, Long surveyId) {
        if(!question.getSurvey().getId().equals(surveyId)){
            throw new SurveyNotFoundException(surveyId);
        }
    }
}
