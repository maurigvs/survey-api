package br.com.maurigvs.surveyapi.exception;

import br.com.maurigvs.surveyapi.dto.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(BadRequestException ex){
        return getErrorMessage(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        var messages = ex.getFieldErrors().stream().map(
            error -> ("The field [" + error.getField() + "] " + error.getDefaultMessage())
        ).toList();
        return getErrorMessage(HttpStatus.BAD_REQUEST, messages);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResourceFoundException(NoResourceFoundException ex){
        return getErrorMessage(HttpStatus.NOT_FOUND,
                "Endpoint inexistent or missing required parameters: " +
                        ex.getHttpMethod() + " /" + ex.getResourcePath());
    }

    private ErrorResponse getErrorMessage(HttpStatus status, List<String> messages){
        return new ErrorResponse(status.getReasonPhrase(), messages);
    }

    private ErrorResponse getErrorMessage(HttpStatus status, String message){
        return new ErrorResponse(status.getReasonPhrase(), message);
    }
}
