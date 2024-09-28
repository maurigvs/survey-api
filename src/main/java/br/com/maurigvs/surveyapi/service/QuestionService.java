package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.entity.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends DatabaseService<Question, QuestionRepository> {

    public QuestionService(QuestionRepository repository) {
        super(repository, Question.class);
    }
}
