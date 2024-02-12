package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.Choice;

public class ChoiceNotFoundException extends EntityNotFoundException {

    public ChoiceNotFoundException(Integer choiceId) {
        super(Choice.class.getSimpleName(), choiceId);
    }
}
