package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.entity.Question;

public class QuestionNotFoundException extends NotFoundException {

    public QuestionNotFoundException(Long questionId) {
        super(Question.class, questionId);
    }
}
