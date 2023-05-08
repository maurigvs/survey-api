package br.com.maurigvs.surveyapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private static final String LOG_PREFIX = "Handling {}";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException exception, HttpServletRequest request){
        log.info(LOG_PREFIX, exception.getClass().getCanonicalName());
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request){
        log.info(LOG_PREFIX, exception.getClass().getCanonicalName());
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> messageNoReadable(HttpMessageNotReadableException exception, HttpServletRequest request){
        log.info(LOG_PREFIX, exception.getClass().getCanonicalName());
        return getApiException(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ApiResponse> getApiException(HttpStatus status, String message, String path){
        ApiResponse error = new ApiResponse(status.getReasonPhrase(), message, path);
        log.info("Response to Exception: {}", error);
        return ResponseEntity.status(status).body(error);
    }
}
