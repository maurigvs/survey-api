package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.dto.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleBadRequestException(BadRequestException ex){
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleMethodArgumentNotValid(WebExchangeBindException ex){
        var messages = ex.getFieldErrors().stream().map(error -> ("The field [" + error.getField() + "] " + error.getDefaultMessage())).toArray(String[]::new);
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), messages));
    }
}
