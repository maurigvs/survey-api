package br.com.maurigvs.surveyapi.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(Class<?> entity, Long id) {
        super(entity.getSimpleName() + " not found by Id " + id);
    }
}
