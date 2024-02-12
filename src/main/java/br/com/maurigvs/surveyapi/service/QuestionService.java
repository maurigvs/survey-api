package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Question;

public interface QuestionService {

    void createQuestion(Question question);

    void deleteById(Long questionId, Long surveyId);
}
