package br.com.maurigvs.surveyapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public record ErrorResponse(
        String error,
        String message
){
}