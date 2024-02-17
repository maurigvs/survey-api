package br.com.maurigvs.surveyapi.exception;

public class UserAlreadyExistsException extends BadRequestException {

    public UserAlreadyExistsException() {
        super("User already exists with same email");
    }
}
