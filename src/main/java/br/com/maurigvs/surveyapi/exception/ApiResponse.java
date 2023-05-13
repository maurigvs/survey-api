package br.com.maurigvs.surveyapi.exception;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponse {
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:mm:ss'Z'")
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    
    private String error;
    
    private String message;
    
    private String path;

    public ApiResponse(String error, String message, String path) {
        this.error = error;
        this.message = message;
        this.path = path;
    }
}