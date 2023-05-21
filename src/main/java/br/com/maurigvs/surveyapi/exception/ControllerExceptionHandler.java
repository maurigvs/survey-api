package br.com.maurigvs.surveyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<StandardError> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        StandardError error = new StandardError(status.getReasonPhrase(), message);
        return ResponseEntity.status(status).body(error);
    }
}