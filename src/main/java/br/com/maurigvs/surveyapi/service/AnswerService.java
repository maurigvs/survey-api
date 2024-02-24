package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;

import java.util.List;

public interface AnswerService {

    void create(Answer answer);

    List<Answer> findAll();

    void deleteById(Long answerId);
}
