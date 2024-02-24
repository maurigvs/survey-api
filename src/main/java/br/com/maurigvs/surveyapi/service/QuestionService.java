package br.com.maurigvs.surveyapi.service;

import br.com.maurigvs.surveyapi.model.Question;

public interface QuestionService {

    void create(Question question);

    Question findById(Long questionId);

    void deleteById(Long questionId, Long surveyId);
}
