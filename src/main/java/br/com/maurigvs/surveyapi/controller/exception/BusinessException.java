package br.com.maurigvs.surveyapi.controller.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
