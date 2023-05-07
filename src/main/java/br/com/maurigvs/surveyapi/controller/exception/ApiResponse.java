package br.com.maurigvs.surveyapi.controller.exception;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ApiResponse {
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:mm:ss'Z'")
    private final ZonedDateTime timestamp = ZonedDateTime.now();
    
    private String error;
    
    private String message;
    
    private String path;
}