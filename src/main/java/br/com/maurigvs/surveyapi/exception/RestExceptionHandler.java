package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    public Mono<ErrorResponse> handleBusinessException(BusinessException exception){
        return Mono.just(new ErrorResponse(
                HttpStatus.PRECONDITION_FAILED.getReasonPhrase(),
                exception.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Mono<ErrorResponse> handleRuntimeException(RuntimeException exception){
        return Mono.just(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage()));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Mono<ErrorResponse> handleWebExchangeBindException(WebExchangeBindException exception){
        return Mono.just(new ErrorResponse(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                mapMessages(exception.getFieldErrors().stream())));
    }

    private static String[] mapMessages(Stream<FieldError> fieldErrorStream) {
        return fieldErrorStream
                .map(error -> ("The field [" + error.getField() + "] " + error.getDefaultMessage()))
                .toArray(String[]::new);
    }
}
