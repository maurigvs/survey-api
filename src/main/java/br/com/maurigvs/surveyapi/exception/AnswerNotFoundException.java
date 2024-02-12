package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.Answer;

public class AnswerNotFoundException extends EntityNotFoundException {

    public AnswerNotFoundException(Long entityId) {
        super(Answer.class.getSimpleName(), entityId);
    }
}
