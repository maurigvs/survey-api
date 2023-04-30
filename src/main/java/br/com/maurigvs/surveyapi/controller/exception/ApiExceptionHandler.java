package br.com.maurigvs.surveyapi.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> messageNoReadable(HttpMessageNotReadableException exception, HttpServletRequest request){
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ApiResponse> getApiException(HttpStatus status, String message, String path){
        ApiResponse error = new ApiResponse(status.getReasonPhrase(), message);
        error.setPath(path);
        return ResponseEntity.status(status).body(error);
    }
}
