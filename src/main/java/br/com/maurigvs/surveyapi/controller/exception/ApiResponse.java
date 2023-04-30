package br.com.maurigvs.surveyapi.controller.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public class ApiResponse {
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:mm:ss'Z'")
    private ZonedDateTime timestamp = ZonedDateTime.now();
    private String error;
    private String message;
    private String path;

    public ApiResponse(String error, String message, String path) {
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}