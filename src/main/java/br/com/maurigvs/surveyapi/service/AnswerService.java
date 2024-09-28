package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.entity.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends DatabaseService<Answer, AnswerRepository> {

    public AnswerService(AnswerRepository repository) {
        super(repository, Answer.class);
    }
}
