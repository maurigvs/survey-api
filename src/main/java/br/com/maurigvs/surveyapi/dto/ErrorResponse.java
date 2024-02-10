package br.com.maurigvs.surveyapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema
public record ErrorResponse(

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T' HH:ss:ss'Z'")
        ZonedDateTime timestamp,
        String error,
        String message
){
}