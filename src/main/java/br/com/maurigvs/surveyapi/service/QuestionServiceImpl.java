package br.com.maurigvs.surveyapi.service;

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
}
