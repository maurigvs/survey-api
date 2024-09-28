package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.entity.Choice;

public class ChoiceNotFoundException extends NotFoundException {

    public ChoiceNotFoundException(Long choiceId) {
        super(Choice.class, choiceId);
    }
}
