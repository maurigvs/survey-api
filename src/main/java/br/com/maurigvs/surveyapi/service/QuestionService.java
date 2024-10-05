package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Question;
import br.com.maurigvs.surveyapi.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends DataAdapter<Question, QuestionRepository> {

    public QuestionService(QuestionRepository repository) {
        super(repository);
    }
}
