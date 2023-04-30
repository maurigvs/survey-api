package br.com.maurigvs.surveyapi.controller.exception;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
public class ApiResponse {
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:mm:ss'Z'")
    @Setter(AccessLevel.NONE)
    private ZonedDateTime timestamp = ZonedDateTime.now();

    @NonNull
    private String error;

    @NonNull
    private String message;

    private String path;
}