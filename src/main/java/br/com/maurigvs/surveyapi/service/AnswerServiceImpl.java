package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.exception.AnswerNotFoundException;
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
    public void create(Answer answer) {
        repository.save(answer);
    }

    private Answer findById(Long answerId){
        return repository.findById(answerId)
                .orElseThrow(() -> new AnswerNotFoundException(answerId));
    }

    @Override
    public List<Answer> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long answerId) {
        var answer = findById(answerId);
        repository.delete(answer);
    }
}
