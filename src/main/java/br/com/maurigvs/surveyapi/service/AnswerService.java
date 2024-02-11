package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Answer;

import java.util.List;

public interface AnswerService {

    void createAnswer(Answer answer);

    List<Answer> findAll();
}
