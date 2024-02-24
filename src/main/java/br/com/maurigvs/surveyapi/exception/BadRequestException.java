package br.com.maurigvs.surveyapi.exception;

abstract class BadRequestException extends RuntimeException {

    BadRequestException(String message) {
        super(message);
    }
}
