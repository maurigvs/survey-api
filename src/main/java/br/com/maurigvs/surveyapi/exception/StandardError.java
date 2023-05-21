package br.com.maurigvs.surveyapi.exception;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:ss:ss'Z'")
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    private final String error;
    private final String message;

    public StandardError(String error, String message) {
        this.error = error;
        this.message = message;
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
}