package br.com.maurigvs.surveyapi.exception;

public abstract class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(String elementName, Integer elementId) {
        super(elementName + " not found by Id " + elementId);
    }
}
