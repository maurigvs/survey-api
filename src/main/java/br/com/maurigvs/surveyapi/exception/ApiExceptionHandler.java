package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.dto.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleBadRequestException(NoSuchElementException ex){
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleBadRequestException(BadRequestException ex){
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()));
    }
}
