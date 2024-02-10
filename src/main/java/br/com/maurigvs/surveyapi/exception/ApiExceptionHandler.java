package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleMethodArgumentNotValid(BadRequestException ex){
        return getErrorMessageDto(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        return getErrorMessageDto(HttpStatus.BAD_REQUEST, ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    private ErrorMessageDto getErrorMessageDto(HttpStatus status, String message){
        return new ErrorMessageDto(ZonedDateTime.now(), status.getReasonPhrase(), message);
    }
}