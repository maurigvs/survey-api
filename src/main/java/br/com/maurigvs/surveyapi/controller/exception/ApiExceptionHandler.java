package br.com.maurigvs.surveyapi.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request){
        log.info("Handling {}", exception.getClass().getCanonicalName());
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> messageNoReadable(HttpMessageNotReadableException exception, HttpServletRequest request){
        log.info("Handling {}", exception.getClass().getCanonicalName());
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ApiResponse> getApiException(HttpStatus status, String message, String path){
        ApiResponse error = new ApiResponse(status.getReasonPhrase(), message, path);
        log.info("Response to Exception: {}", error);
        return ResponseEntity.status(status).body(error);
    }
}
