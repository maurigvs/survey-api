package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Mono<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        return Mono.just(new ErrorResponse(HttpStatus.PRECONDITION_FAILED, ex.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleMethodArgumentNotValid(WebExchangeBindException ex) {
        List<String> messages = ex.getFieldErrors().stream()
                .map(error -> ("The field [" + error.getField() + "] " + error.getDefaultMessage()))
                .toList();
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), messages));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Mono<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return Mono.just(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage()));
    }
}
