package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;
import br.com.maurigvs.surveyapi.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository repository;

    public AnswerServiceImpl(AnswerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createAnswer(Answer answer) {
        repository.save(answer);
    }

    @Override
    public List<Answer> findAll() {
        return repository.findAll();
    }
}
