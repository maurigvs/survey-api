package br.com.maurigvs.surveyapi.exception;

public abstract class EntityNotFoundException extends BadRequestException {

    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + " not found by Id " + entityId);
    }
}
