package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.Question;

public class QuestionNotFoundException extends EntityNotFoundException {

    public QuestionNotFoundException(Integer questionId) {
        super(Question.class.getSimpleName(), questionId);
    }
}
