package br.com.maurigvs.surveyapi.exception;

public class ChoiceNotFoundException extends BadRequestException {

    public ChoiceNotFoundException(Long choiceId) {
        super("Choice not found by Id " + choiceId);
    }
}
