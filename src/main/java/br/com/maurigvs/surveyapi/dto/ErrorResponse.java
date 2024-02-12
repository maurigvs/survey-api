package br.com.maurigvs.surveyapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.ZonedDateTime;

@Schema
public record ErrorResponse(
        String error,
        String message
){
}