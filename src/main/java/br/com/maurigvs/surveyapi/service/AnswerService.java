package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService extends DataAdapter<Answer, AnswerRepository> {

    public AnswerService(AnswerRepository repository) {
        super(repository);
    }
}
