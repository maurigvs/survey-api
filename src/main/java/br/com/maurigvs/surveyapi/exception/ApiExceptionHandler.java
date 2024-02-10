package br.com.maurigvs.surveyapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        return new ErrorMessageDto(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }
}